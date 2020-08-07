package br.com.gianvittorio.data.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.Date;

public class UploadFileResponseVO implements Serializable {
    private static final long serialVersionUID = -8498307030944916368L;

    private String fileName;
    private String fileDownloadURI;
    private String FileType;
    private long size;

    public UploadFileResponseVO() {
    }

    public UploadFileResponseVO(String fileName, String fileDownloadURI, String fileType, long size) {
        this.fileName = fileName;
        this.fileDownloadURI = fileDownloadURI;
        FileType = fileType;
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDownloadURI() {
        return fileDownloadURI;
    }

    public void setFileDownloadURI(String fileDownloadURI) {
        this.fileDownloadURI = fileDownloadURI;
    }

    public String getFileType() {
        return FileType;
    }

    public void setFileType(String fileType) {
        FileType = fileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
