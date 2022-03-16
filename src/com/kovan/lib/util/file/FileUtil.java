package com.kovan.lib.util.file;

import com.kovan.lib.util.string.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class FileUtil {
    public static final String ERROR_DIRECTORY_NOT_FOUND = "ES0100";
    public static final String ERROR_FILE_NOT_FOUND = "ES0101";
    public static final String ERROR_FILE_RENAME = "ES0102";
    public static final String ERROR_FILE_DELETE = "ES0103";
    public static final String ERROR_FILE_MOVE = "ES0104";
    public static final String ERROR_FILE_MAKE = "ES0105";
    public static final String ERROR_FILE_OPEN = "ES0106";
    public static final String ERROR_FILE_CLOSE = "ES0107";
    public static final String INITIAL_VECTOR = "testivivtestiviv";
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static Charset encoding = Charset.defaultCharset();
    public File file;
    public PrintWriter pw;
    public OutputStreamWriter osw;
    public FileOutputStream fos;
    public int rowCnt;
    private static FileUtil m_instance = null;
    private static String separator;

    public FileUtil() {
    }

    public static FileUtil getInstance() {
        if (m_instance == null) {
            m_instance = new FileUtil();
        }

        return m_instance;
    }

    public static void setEncoding(Charset enc) {
        encoding = enc;
    }

    public boolean makeFile(String filePath, String fileName, String tempPath) {
        boolean result = true;
        this.file = null;
        this.logger.debug("filePath : " + filePath);
        this.logger.debug("fileName : " + fileName);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

        try {
            this.file = new File(filePath);
            if (!this.file.exists()) {
                this.logger.error(StringUtil.getErrorLogForEMS("ES0100", "[" + this.file.getParentFile() + "] does not exist."));
                return false;
            }

            this.file = new File(filePath + separator + fileName);
            String newName = "";
            if (this.file.exists()) {
                newName = fileName + "." + dateFormat.format(new Date());
                this.logger.debug("A file with the same name exist.");
                this.logger.debug("Change the file name. " + fileName + " >>> " + newName);
                result = this.renameFile(filePath + separator + fileName, filePath + separator + newName);
                if (!result) {
                    this.logger.error(StringUtil.getErrorLogForEMS("ES0102", "[" + filePath + separator + newName + "] Rename File."));
                    return result;
                }

                if (!tempPath.equals("") && !tempPath.isEmpty()) {
                    result = this.moveFile(filePath + separator + newName, tempPath + separator + newName);
                    if (!result) {
                        this.logger.error(StringUtil.getErrorLogForEMS("ES0104", "[" + tempPath + separator + newName + "] Move File."));
                        return result;
                    }
                }
            }

            this.logger.debug("Create a file. [" + filePath + separator + fileName + "]");
            this.file.createNewFile();
            this.openFile();
            this.rowCnt = 0;
        } catch (IOException var7) {
            this.logger.error(StringUtil.getErrorLogForEMS("ES0105", this.getPrintStacTraceString(var7)));
        } catch (Exception var8) {
            this.logger.error(StringUtil.getErrorLogForEMS("ES0105", this.getPrintStacTraceString(var8)));
        }

        return result;
    }

    public boolean renameFile(String fileName, String newName) {
        this.logger.debug("Filename:" + fileName + " NewName:" + newName);
        File newFile = new File(newName);
        if (this.file == null) {
            this.logger.debug("Filename is null... create new File");
            this.file = new File(fileName);
        }

        return this.file.renameTo(newFile);
    }

    public boolean moveFile(String fileName, String newName) {
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        File oldFile = new File(fileName);
        if (!oldFile.exists()) {
            this.logger.debug("[ERROR]\t[" + fileName + "] does not exist.");
            return false;
        } else {
            File newFile = new File(newName);
            if (!newFile.getParentFile().exists()) {
                this.logger.debug("[DEBUG]\t[" + newFile.getParentFile().getPath() + "] does not exist.");
                this.logger.debug("[DEBUG]\t[" + newFile.getParentFile().getPath() + "] make directory.");
                newFile.getParentFile().mkdir();
            }

            if (!newFile.getParentFile().isDirectory()) {
                this.logger.debug("[ERROR]\t[" + newFile.getParentFile().getPath() + "] It is not a directory format.");
                return false;
            } else {
                if (newFile.exists()) {
                    this.logger.debug("[DEBUG]\t[" + newName + "] exists.");
                    this.logger.debug("[DEBUG]\t[" + newName + "] move to [" + newName + "." + dateFormat.format(new Date()) + "]");
                    File tmpFile = new File(newName + "." + dateFormat.format(new Date()));
                    newFile.renameTo(tmpFile);
                }

                this.logger.debug("[DEBUG]\t[" + fileName + "] move to [" + newName + "]");
                return oldFile.renameTo(newFile);
            }
        }
    }

    public void openFile() {
        try {
            if (this.file != null) {
                this.fos = new FileOutputStream(this.file, true);
                this.osw = new OutputStreamWriter(this.fos, encoding);
                this.pw = new PrintWriter(this.osw);
            } else {
                this.closeFile();
            }
        } catch (FileNotFoundException var2) {
            this.logger.error(StringUtil.getErrorLogForEMS("ES0106", this.getPrintStacTraceString(var2)));
        } catch (Exception var3) {
            this.logger.error(StringUtil.getErrorLogForEMS("ES0106", this.getPrintStacTraceString(var3)));
        }

    }

    public void write(StringBuffer sb) {
        if (this.pw != null) {
            this.pw.println(new String(sb.toString().getBytes(encoding), encoding));
            ++this.rowCnt;
        } else {
            this.closeFile();
        }

    }

    public void writeNoLn(StringBuffer sb) {
        if (this.pw != null) {
            this.pw.print(new String(sb.toString().getBytes(encoding), encoding));
            ++this.rowCnt;
        } else {
            this.closeFile();
        }

    }

    public void closeFile() {
        try {
            if (this.pw != null) {
                this.pw.close();
            }

            if (this.osw != null) {
                this.osw.close();
            }

            if (this.fos != null) {
                this.fos.close();
            }
        } catch (IOException var6) {
            this.logger.error(StringUtil.getErrorLogForEMS("ES0107", this.getPrintStacTraceString(var6)));
        } catch (Exception var7) {
            this.logger.error(StringUtil.getErrorLogForEMS("ES0107", this.getPrintStacTraceString(var7)));
        } finally {
            if (this.file != null) {
                this.file = null;
            }

        }

    }

    public String getPrintStacTraceString(Exception e) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream pinrtStream = new PrintStream(out);
        e.printStackTrace(pinrtStream);
        return out.toString();
    }

    public List<File> getFileList(String dir) {
        String processName = "[getFileList]";
        List<File> fileList = new ArrayList();
        File file = null;
        if (!dir.equals("") && !dir.isEmpty()) {
            this.logger.debug(processName + "\tsearch Directory " + dir);
            file = new File(dir);
            if (!file.isDirectory()) {
                this.logger.error(StringUtil.getErrorLogForEMS("ES0100", "It is not a directory." + dir));
            } else {
                File[] fileArray = file.listFiles();
                if (fileArray != null) {
                    File[] var6 = fileArray;
                    int var7 = fileArray.length;

                    for(int var8 = 0; var8 < var7; ++var8) {
                        File f = var6[var8];
                        if (!f.isDirectory()) {
                            fileList.add(f.getAbsoluteFile());
                            this.logger.debug(processName + "\t[File]" + f.getName());
                        }
                    }

                    Collections.sort(fileList);
                    this.logger.debug(processName + "\treturn fileList size : " + fileList.size());
                }
            }
        } else {
            this.logger.error(StringUtil.getErrorLogForEMS("ES0100", "directory name is null."));
        }

        return fileList;
    }

    static {
        separator = File.separator;
    }
}
