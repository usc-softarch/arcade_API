//TODO THIS DOES NOT WORK, source is hard-coded beyond all redemption

// package edu.usc.softarch.arcade.frontend.features.depsbuilder;
//
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.junit.runners.Parameterized;
// import java.io.File;
// import java.util.Collection;
// import java.util.Arrays;
// import edu.usc.softarch.arcade.frontend.features.FeatureWrapper;
// import edu.usc.softarch.arcade.frontend.arghandlers.SourceDir;
// import edu.usc.softarch.arcade.frontend.arghandlers.CloneDir;
// import edu.usc.softarch.arcade.frontend.arghandlers.ClusterDir;
// import edu.usc.softarch.arcade.frontend.arghandlers.DepFinderDir;
// import edu.usc.softarch.arcade.frontend.arghandlers.CleanUpCodeMaatFile;
// import edu.usc.softarch.arcade.frontend.arghandlers.PkgPrefixes;
// import edu.usc.softarch.arcade.frontend.arghandlers.InterfaceSmellFile;
//
// @RunWith(Parameterized.class)
// public class DependencyFinderProcessing_ExportJSONWrapperTest
// {
//   //#region ATTRIBUTES
//   String sourceDir;
//   String cloneDir;
//   String clusterDir;
//   String depFinderDir;
//   String cleanUpCodeMaatFile;
//   String pkgPrefixes;
//   String interfaceSmellFile;
//   //#endregion
//
//   //#region CONSTRUCTOR
//   public DependencyFinderProcessing_ExportJSONWrapperTest(String sourceDir, String cloneDir, String clusterDir, String depFinderDir, String cleanUpCodeMaatFile, String pkgPrefixes, String interfaceSmellFile)
//   {
//     super();
//     this.sourceDir = sourceDir;
//     this.cloneDir = cloneDir;
//     this.clusterDir = clusterDir;
//     this.depFinderDir = depFinderDir;
//     this.cleanUpCodeMaatFile = cleanUpCodeMaatFile;
//     this.pkgPrefixes = pkgPrefixes;
//     this.interfaceSmellFile = interfaceSmellFile;
//   }
//   //#endregion
//
//   //#region PARAMETERS
//   @Parameterized.Parameters
//   public static Collection<Object[]> input()
//   {
//     String fs = File.separator;
//     return Arrays.asList(new Object[][]
//     {
// //    	this component is an exception, whoever built it, built all input arguments as incremental from src dir, hence, can't apply arghandlers to validate input dir
//       {
//     	  "src" + fs + "test" + fs + "resources" + fs + "subject_systems" + fs + "Struts2",
//     	  "clone" + fs + "Struts2",
//     	  "acdc" + fs + "cluster",
//     	  "depfinder",
//     	  "struts2_clean.csv",
//     	  "org.apache.struts2",
//     	  "struts2_acdc_interface_smell.csv"
//       }
//     });
//   }
//   //#endregion
//
//   //#region TESTS
//   /**
//    * Basic positive test for DependencyFinderProcessing_ExportJSON. Takes the input from
//    * Struts2 extracted files and
//    * generates _interface_smells.csv files from it.
//    */
//   @Test
//   public void testDependencyFinderProcessing_ExportJSONWrapper()
//   {
//     FeatureWrapper testDependencyFinderProcessing_ExportJSON = new DependencyFinderProcessing_ExportJSONWrapper();
//     SourceDir.getInstance().setValue(sourceDir);
//     CloneDir.getInstance().setValue(cloneDir);
//
//     try
//     {
//       testDependencyFinderProcessing_ExportJSON.checkArguments(false);
//       testDependencyFinderProcessing_ExportJSON.execute();
//     }
//     catch(Exception e)
//     {
//       e.printStackTrace();
//     }
//
//   }
//
//   /**
//    * Negative test for DependencyFinderProcessing_ExportJSONWrapper. Takes in random, non _interface_smells.csv, Expected output
//    * unknown.
//    */
//   @Test
//   public void randomFilesTestDependencyFinderProcessing_ExportJSON()
//   {
//     //TODO
//   }
//   //#endregion
// }
