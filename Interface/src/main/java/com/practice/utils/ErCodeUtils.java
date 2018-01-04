package com.practice.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 二维码 工具类
 * @author Xushd on 2017/12/9 14:14
 */
public class ErCodeUtils {

    /**
     * Ercode create
     * @param os
     * @param ercode
     */
    public static void createErCode(OutputStream os, String ercode){

        if (ercode != null && !"".equals(ercode)) {
            try {
                //图片的宽度
                int width = 150;
                //图片的高度
                int height = 150;
                QRCodeWriter writer = new QRCodeWriter();
                BitMatrix m = writer.encode(ercode, BarcodeFormat.QR_CODE, height, width);
                //删除白边
                m = deleteWhite(m);
                MatrixToImageWriter.writeToStream(m, "png", os);

            } catch (WriterException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Delete white border
     * @param matrix
     * @return
     */
    private static BitMatrix deleteWhite(BitMatrix matrix) {
        int[] rec = matrix.getEnclosingRectangle();
        int resWidth = rec[2] + 1;
        int resHeight = rec[3] + 1;

        BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
        resMatrix.clear();
        for (int i = 0; i < resWidth; i++) {
            for (int j = 0; j < resHeight; j++) {
                if (matrix.get(i + rec[0], j + rec[1]))
                    resMatrix.set(i, j);
            }
        }
        return resMatrix;
    }

}
