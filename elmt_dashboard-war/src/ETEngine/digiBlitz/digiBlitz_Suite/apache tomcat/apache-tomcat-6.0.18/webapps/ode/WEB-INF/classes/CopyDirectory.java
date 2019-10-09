/*******************************************************************************
 * * * Copyright: 2019 digiBlitz Foundation
 *  * * 
 *  * * License: digiBlitz Public License 1.0 (DPL) 
 *  * * Administered by digiBlitz Foundation. www.digiblitz.org/dpl/
 *  * * 
 *  * * Inventor: Suresh Kannan (Maya Suresh Kannan Balabisegan ) (www.sureshkannan.org)
 *  * * 
 *  * * Authors: Suresh Kannan (Maya Suresh Kannan Balabisegan )& digiBlitz.
 *  * * 
 *  * * "Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software in accordance to the rules & restrictions of the digiBlitz Public License."
 ******************************************************************************/
import java.io.*;

public class CopyDirectory{

public void copyDirectory(File srcPath, File dstPath)
                             throws IOException{

if (srcPath.isDirectory()){

    if (!dstPath.exists()){

      dstPath.mkdir();

   }


   String files[] = srcPath.list();

  for(int i = 0; i < files.length; i++){
      copyDirectory(new File(srcPath, files[i]), 
                   new File(dstPath, files[i]));

    }

  }

 else{

    if(!srcPath.exists()){

      System.out.println("File or directory does not exist.");

     System.exit(0);

    }
    
else

    {

	FileInputStream in = new FileInputStream(srcPath);
	FileOutputStream out = new FileOutputStream(dstPath); 
                   // Transfer bytes from in to out
          byte[] buf = new byte[1024];

            int len;

         while ((len = in.read(buf)) > 0) {

        out.write(buf, 0, len);

      }

     in.close();

         out.close();

    }

 }
 
System.out.println("Directory copied.");

}

}
