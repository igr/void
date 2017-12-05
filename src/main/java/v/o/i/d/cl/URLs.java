package v.o.i.d.cl;

import jodd.Jodd;

import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class URLs {
	public static void main(String[] args) throws URISyntaxException {

		listSystemModules();

		HashSet<ModuleDescriptor> allModDescs = new HashSet<>();

		Module module = URLs.class.getModule();
		System.out.println(module.getClassLoader().getResource(""));
		System.out.println(Jodd.class.getClassLoader().getResource("META-INF/MANIFEST.MF"));

		System.out.println(module.getClassLoader().getResource(classNameToResourceName(URLs.class)));
		System.out.println(Jodd.class.getClassLoader().getResource(classNameToResourceName(Jodd.class)));

		allModDescs.add(module.getDescriptor());

		ModuleFinder finder = ModuleFinder.of(Paths.get("/Users/igsp/prj/igr/void/out/production/", "/Users/igsp/.gradle-igsp/caches/modules-2/files-2.1/"));
		Set<ModuleReference> moduleReferences = finder.findAll();

		System.out.println("----------");

		allModDescs.stream()
			.sorted()
			.forEach((ModuleDescriptor mod) -> {
				System.out.println("\nModule " + mod.toNameAndVersion() + " (automatic: " + mod.isAutomatic() + ")");

				mod.requires()
					.stream()
					.sorted()
					.forEach((ModuleDescriptor.Requires req) -> {
						System.out.println(
							"\trequires " + req.name()
							+ ((req.modifiers().isEmpty()) ? "" : " " + req.modifiers()));

						ModuleLayer.boot()
							.findModule(req.name())
							.ifPresent(module1 -> {
								ClassLoader cl = module1.getClassLoader();
								if (cl != null) {
									System.out.println("\t" + cl.getResource("META-INF/MANIFEST.MF"));
								}
							});
					});


			});
	}

	private static String classNameToResourceName(Class type) {
		return type.getName().replace('.', '/') + ".class";
	}

	private static void listSystemModules() {
		ModuleFinder.ofSystem().findAll()
			.forEach((ModuleReference modRef) -> {
				System.out.println(modRef.location());
			});
	}
}
