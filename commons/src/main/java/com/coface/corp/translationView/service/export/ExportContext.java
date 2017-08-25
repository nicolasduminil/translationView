package com.coface.corp.translationView.service.export;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ExportContext
{
  private static final Log LOG = LogFactory.getLog(ExportContext.class);
  private String id = Thread.currentThread().getId() + "_" + System.currentTimeMillis();
  private String resultFilePath;
  private File resultFile;

  public String getId()
  {
    return id;
  }

  public void setFilePath(String path)
  {
    this.resultFilePath = path;
  }

  public String getFilePath()
  {
    return this.resultFilePath;
  }

  public File createTempDirectory()
  {
    String tmpDir = System.getProperty("java.io.tmpdir");
    File folder = new File(getTempDirectoryName());
    if (folder.exists())
    {
      File[] filesList = folder.listFiles();
      for (int i = 0; i < filesList.length; i++)
      {
        filesList[i].delete();
      }
    }
    else
    {
      if (!new File(tmpDir).exists())
      {
        new File(tmpDir).mkdir();
      }
      folder.mkdir();
    }
    return folder;
  }

  public void releaseResources()
  {
    deleteTempDirectory(new File(getTempDirectoryName()));
  }

  private String getTempDirectoryName()
  {
    return System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + this.id;
  }

  private void deleteTempDirectory(File folder)
  {
    if (folder != null && folder.exists() && folder.isDirectory())
    {
      String[] files = folder.list();
      boolean success;
      String filename;
      for (int i = 0; i < files.length; i++)
      {
        filename = folder.getAbsolutePath() + System.getProperty("file.separator") + files[i];
        success = new File(filename).delete();
        if (!success)
        {
          LOG.warn("Unable to delete file " + filename);
        }
      }
      success = folder.delete();
      if (!success)
      {
        LOG.warn("Unable to delete folder " + folder.getAbsolutePath());
      }
    }
  }

  public File getResultFile()
  {
    return resultFile;
  }

  public void setResultFile(File resultFile)
  {
    this.resultFile = resultFile;
  }
}
