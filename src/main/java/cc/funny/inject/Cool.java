package cc.funny.inject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ClassUtils;

public class Cool {

	private static final List<String> classNames = new ArrayList<>();
	
	private static boolean inited = false;
	
	
	public static List<Class<?>> scan() {
		ClassLoader cl = Thread.currentThread().getContextClassLoader();

		Path path;
		try {
			path = Paths.get(cl.getResource("").toURI());
			Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file,
						BasicFileAttributes attrs) throws IOException {
					if (file.toString().endsWith(".class")) {
						Path classPath = path.relativize(file);
						String className = classPath.toString();
						className = className.replace(File.separatorChar, '.')
								.substring(0, className.length() - 6);
						classNames.add(className);
					}
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
		}
		return ClassUtils.convertClassNamesToClasses(classNames);
	}

	private static final Map<Class<?>, Object> CLASSPOOL = new HashMap<>();

	public static void init(){
		List<Class<?>> list = scan();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			Class<?> clazz = list.get(i);
			Field[] fields = clazz.getDeclaredFields();
			int length = fields.length;
			for (int j = 0; j < length; j++) {
				// get the fields to be inject
				if (fields[j].getAnnotation(Inject.class) != null) {
					//inject into List field...
					if (List.class.isAssignableFrom(fields[j].getType())) {
						fields[j].setAccessible(true);
						Object instance = null;
						try {
							instance = clazz.newInstance();
							CLASSPOOL.put(clazz, instance);
							Class<?> c = getClass(list,
									((ParameterizedType) fields[j]
											.getGenericType())
											.getActualTypeArguments()[0]);
							fields[j].set(instance, getValues(list, c));
						} catch (IllegalArgumentException
								| IllegalAccessException
								| InstantiationException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
	}

	private static Class<?> getClass(List<Class<?>> list, Type type) {
		int index = -1;
		if ((index = classNames.indexOf(type.getTypeName())) > -1) {
			return list.get(index);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private static <T> List<T> getValues(List<Class<?>> list, Class<T> clazz)
			throws InstantiationException, IllegalAccessException {
		List<T> result = new ArrayList<>();
		int size = list.size();
		for (int i = 0; i < size; i++) {
			Class<?> tmp = list.get(i);
			if (clazz.isAssignableFrom(tmp) && !tmp.isInterface()) {
				Object instance = CLASSPOOL.get(tmp);
				if (instance == null) {
					instance = tmp.newInstance();
					CLASSPOOL.put(tmp, instance);
				}
				result.add((T) instance);
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public static <T> T getBean(Class<T> clazz) {
		if(!inited) {
			init();
			inited = true;
		}
		return (T) CLASSPOOL.get(clazz);
	}
	
}
