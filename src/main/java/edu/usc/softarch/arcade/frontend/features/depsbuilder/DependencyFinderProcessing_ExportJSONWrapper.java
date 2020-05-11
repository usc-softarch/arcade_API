//TODO THIS DOES NOT WORK, source is hard-coded beyond all redemption

// package edu.usc.softarch.arcade.frontend.features.smelldetection;
//
// import edu.usc.softarch.arcade.antipattern.detection.interfacebased.DependencyFinderProcessing_ExportJSON;
// import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
//
// import edu.usc.softarch.arcade.frontend.arghandlers.ArgHandler;
// import edu.usc.softarch.arcade.frontend.arghandlers.SourceDir;
// import edu.usc.softarch.arcade.frontend.arghandlers.ClusterDir;
// import edu.usc.softarch.arcade.frontend.arghandlers.DepFinderDir;
// import edu.usc.softarch.arcade.frontend.arghandlers.CloneDir;
// import edu.usc.softarch.arcade.frontend.arghandlers.CleanUpCodeMaatFile;
// import edu.usc.softarch.arcade.frontend.arghandlers.PkgPrefixes;
// import edu.usc.softarch.arcade.frontend.arghandlers.InterfaceSmellFile;
//
// // only works with Java source code, because DepFinderDir only available in Java
//
// public class DependencyFinderProcessing_ExportJSONWrapper
//   extends FeatureWrapper
// {
//   //#region CONSTRUCTORS
//   public DependencyFinderProcessing_ExportJSONWrapper()
//   {
//     String id = "dependencyFinderProcessing_ExportJSON";
//     String name = "Dependency Finder Processing and Export to JSON";
//     ArgHandler[] requiredArguments =
//     {
//       SourceDir.getInstance(),
//       DepFinderDir.getInstance(),
//       ClusterDir.getInstance(),
//       CloneDir.getInstance(),
//       CleanUpCodeMaatFile.getInstance(),
//       PkgPrefixes.getInstance(),
//       InterfaceSmellFile.getInstance()
//     };
//     initialize(id, name, requiredArguments);
//   }
//   //#endregion
//
//   //#region EXECUTION
//   @Override
//   public void execute()
//     throws Exception
//   {
//     String[] parsedArgs = new String[7];
//     parsedArgs[0] = ClusterDir.getInstance().getValue();
//     parsedArgs[1] = DepFinderDir.getInstance().getValue();
//     parsedArgs[2] = SourceDir.getInstance().getValue();
//     parsedArgs[3] = CloneDir.getInstance().getValue();
//     parsedArgs[4] = CleanUpCodeMaatFile.getInstance().getValue();
//     parsedArgs[5] = PkgPrefixes.getInstance().getValue();
//     parsedArgs[6] = InterfaceSmellFile.getInstance().getValue();
//     DependencyFinderProcessing_ExportJSON.main(parsedArgs);
//   }
//   //#endregion
//
//   //#region VALIDATION
//   @Override
//   public boolean checkArguments(boolean checkOptional)
//     throws Exception
//   {
//     boolean clusterDirValid = ClusterDir.getInstance().validateAsInput();
//     boolean sourceDirPathValid = SourceDir.getInstance().validateAsInput();
//     boolean depFinderDirValid = DepFinderDir.getInstance().validateAsInput();
//     boolean cloneDirValid = CloneDir.getInstance().validateAsInput();
//     boolean cleanUpCodeMaatFilePathValid = CleanUpCodeMaatFile.getInstance().validateAsInput();
//     boolean pkgPrefixesValid = PkgPrefixes.getInstance().validateAsInput();
//     boolean interfaceSmellFileValid = InterfaceSmellFile.getInstance().validateAsOutput();
//
//     return (clusterDirValid && sourceDirPathValid && depFinderDirValid
//     		&& cloneDirValid && cleanUpCodeMaatFilePathValid && pkgPrefixesValid
//     		&& interfaceSmellFileValid);
//   }
//   //#endregion
// }
