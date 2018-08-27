package com.zhskg.bag.util;

import org.apache.commons.lang.StringUtils;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by luochaojun on 2017/10/12.
 */
public class FastDFSUtil {
    private static TrackerServer trackerServer = null;
    private static StorageServer storageServer = null;
    private static TrackerClient trackerClient = null;
    private static StorageClient storageClient = null;

    private static void getStorageClient() {
        try {
           // ClientGlobal.setG_connect_timeout(3000);
            ClientGlobal.initByTrackers(ConfigUtil.FAST_TRACKERS_IP);
            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            storageClient = new StorageClient(trackerServer, storageServer);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String upload_File(String filePath) {
        try {
            getStorageClient();
            if (storageClient == null) {
                return null;
            }
            String[] fileTypes = filePath.split("\\.");
            Integer fileTypesLen = fileTypes.length;
            String[] fileIds = storageClient.upload_file(filePath, fileTypes[fileTypesLen - 1], null);
            return StringUtils.join(fileIds, "/");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param inputStream
     * @param extName
     * @return
     */
    public static String upload_file(InputStream inputStream, String extName) {
        try {
            getStorageClient();
            if (storageClient == null) {
                return null;
            }
            byte[] fileByte = ByteArrayUtil.toByteArray(inputStream);
        /*    NameValuePair[] nameValuePair = new NameValuePair[3];
            nameValuePair[0] = new NameValuePair("overdueFlag", "1");
            nameValuePair[2] = new NameValuePair("termOfValidity", "30");
            nameValuePair[1] = new NameValuePair("createTime", String.valueOf(System.currentTimeMillis()));*/
            synchronized (storageClient) {
                String[] files = storageClient.upload_file(fileByte, extName, null);
                return StringUtils.join(files, "/");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * @param fileByte
     * @param extName
     * @return
     */
    public static String upload_file(byte[] fileByte, String extName) {
        try {
            getStorageClient();
            if (storageClient == null) {
                return null;
            }
            synchronized (storageClient) {
                String[] files = storageClient.upload_file(fileByte, extName, null);
                return StringUtils.join(files, "/");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 通过文件路径删除
     *
     * @param filePath
     * @return
     */
    public static boolean deleteFile(String filePath) {
        boolean result = false;
        try {
            getStorageClient();
            if (storageClient == null) {
                return false;
            }
            Map<String, String> map = dealFilePath(filePath);
            String groupName = map.get("groupName");
            String remoteFileName = map.get("remoteFileName");
            int delNum = storageClient.delete_file(groupName, remoteFileName);
            trackerServer.close();
            if (delNum == 0) result = true;
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return result;
        }
    }

    public static boolean batchDeleteFile(List<String> filePaths) {
        try {
            if (filePaths != null && filePaths.size() > 0) {
                for (String filePath : filePaths) {
                    deleteFile(filePath);
                }
            }
            return true;
        } catch (Exception ex) {

        }
        return false;
    }

    public static byte[] downloadFile(String filePath) {
        try {
            getStorageClient();
            if (storageClient == null) {
                return null;
            }
            Map<String, String> map = dealFilePath(filePath);
            String groupName = map.get("groupName");
            String remoteFileName = map.get("remoteFileName");
            return storageClient.download_file(groupName, remoteFileName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取NameValuePair
     *
     * @param filePath
     * @return
     */
    public static NameValuePair[] getNameValuePair(String filePath) {
        try {
            getStorageClient();
            if (storageClient == null) {
                return null;
            }
            Map<String, String> map = dealFilePath(filePath);
            String groupName = map.get("groupName");
            String remoteFileName = map.get("remoteFileName");
            return storageClient.get_metadata(groupName, remoteFileName);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 设置NameValuePair
     *
     * @param filePath
     * @return
     */
    public static boolean setNameValuePair(String filePath) {
        boolean rslt = false;
        try {
            getStorageClient();
            if (storageClient == null) {
                return rslt;
            }
            Map<String, String> map = dealFilePath(filePath);
            String remoteFileName = map.get("remoteFileName");
            String groupName = map.get("groupName");
            NameValuePair[] nameValuePair = new NameValuePair[2];
            nameValuePair[0] = new NameValuePair("overdueFlag", "0");
            nameValuePair[1] = new NameValuePair("modifyTime", String.valueOf(System.currentTimeMillis()));
            int num = storageClient.set_metadata(groupName, remoteFileName, nameValuePair, ProtoCommon.STORAGE_SET_METADATA_FLAG_MERGE);
            if (num == 0) {
                rslt = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rslt;
    }

    public static boolean setNameValuePair(String filePath, String overdueFlag) {
        boolean rslt = false;
        try {
            getStorageClient();
            if (storageClient == null) {
                return rslt;
            }
            Map<String, String> map = dealFilePath(filePath);
            String groupName = map.get("groupName");
            String remoteFileName = map.get("remoteFileName");
            NameValuePair[] nameValuePair = new NameValuePair[2];
            nameValuePair[0] = new NameValuePair("overdueFlag", overdueFlag);
            nameValuePair[1] = new NameValuePair("modifyTime", String.valueOf(System.currentTimeMillis()));
            int num = storageClient.set_metadata(groupName, remoteFileName, nameValuePair, ProtoCommon.STORAGE_SET_METADATA_FLAG_MERGE);
            if (num == 0) {
                rslt = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rslt;
    }

    public static boolean setNameValuePair(List<String> filePaths, String overdueFlag) {
        boolean rslt = false;
        try {
            getStorageClient();
            if (storageClient == null) {
                return rslt;
            }
            for (String filePath : filePaths) {
                setNameValuePair(filePath, overdueFlag);
            }
            rslt = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return rslt;
    }

     /**
     * 通过文件路径得到groupName(分组名称),remoteFileName(远程文件名)
     *
     * @param filePath
     * @return
     */
    public static Map dealFilePath(String filePath)
    {
        Map<String, String> map = new HashMap<>();
        String groupName;
        String remoteFileName;
        if (filePath.startsWith("/")) {
            groupName = filePath.substring(1, 7);
            remoteFileName = filePath.substring(8);
        } else {
            remoteFileName = filePath.substring(7);
            groupName = filePath.substring(0, 6);
        }
        map.put("groupName", groupName);
        map.put("remoteFileName", remoteFileName);
        return map;
    }

}
