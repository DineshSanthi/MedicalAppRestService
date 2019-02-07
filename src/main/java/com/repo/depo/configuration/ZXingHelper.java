package com.repo.depo.configuration;

import java.io.ByteArrayOutputStream;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class ZXingHelper {

	public static byte[] getQRCodeImage(String text, int width, int height, String collectionName) {
		try {
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			 String filePath = "C:\\QRCODE\\"+collectionName+"_"+"text"+".png";
			BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, "png", byteArrayOutputStream);
			return byteArrayOutputStream.toByteArray();
		} catch (Exception e) {
			return null;
		}
	}

}