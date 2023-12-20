package com;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageFilter {

	public static void main(String[] args) {
		try {
			// 이미지 파일 경로 지정
			String inputImagePath = "input.png";
			String outputImagePath = "output.png";

			// 이미지 파일 읽기
			BufferedImage originalImage = ImageIO.read(new File(inputImagePath));

			// 이미지 필터링
			BufferedImage filteredImage = applyFilter(originalImage);

			// 필터링된 이미지 저장
			ImageIO.write(filteredImage, "png", new File(outputImagePath));

			System.out.println("이미지 필터링이 완료되었습니다.");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static BufferedImage applyFilter(BufferedImage originalImage) {
		// 새로운 이미지 생성 (원본 이미지와 같은 크기 및 타입)
		BufferedImage filteredImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(),
				originalImage.getType());

		// 필터링 수행
		for (int y = 0; y < originalImage.getHeight(); y++) {
			for (int x = 0; x < originalImage.getWidth(); x++) {
				// 각 픽셀의 RGB 값 가져오기
				int rgb = originalImage.getRGB(x, y);
				Color color = new Color(rgb);

				// 각 색상 채널을 조정하여 필터링 수행
				int red = color.getRed();
				int green = color.getGreen();
				int blue = color.getBlue();

				if (red == 0 && green == 164 && blue == 0) {
					filteredImage.setRGB(x, y, rgb);
				} else {
					// 나머지 픽셀은 투명 처리
					filteredImage.setRGB(x, y, 0x00FFFFFF & rgb);
				}
			}
		}

		return filteredImage;
	}
}
