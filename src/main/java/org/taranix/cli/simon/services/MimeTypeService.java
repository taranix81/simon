package org.taranix.cli.simon.services;

import org.taranix.cafe.beans.annotations.CafeService;

@CafeService
public class MimeTypeService {

    public String getFileExtensionFromMimeType(String mimeType) {
        if (mimeType == null || mimeType.isEmpty()) {
            return "bin";
        }
        switch (mimeType.toLowerCase()) {
            case "image/jpeg":
            case "image/jpg":
                return "jpg";
            case "image/png":
                return "png";
            case "image/gif":
                return "gif";
            case "application/pdf":
                return "pdf";
            case "audio/mpeg":
                return "mp3";
            case "video/mp4":
                return "mp4";
            case "text/plain":
                return "txt";
            case "text/html":
                return "html";
            case "application/json":
                return "json";
            default:
                int slashIndex = mimeType.lastIndexOf('/');
                if (slashIndex != -1 && slashIndex < mimeType.length() - 1) {
                    return mimeType.substring(slashIndex + 1);
                }
                return "bin";
        }
    }

}
