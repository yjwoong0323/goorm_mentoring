package me.james;

class HighResolutionImage {
  String img;

  HighResolutionImage(String path) {
    loadImage(path);
  }

  private void loadImage(String path) {
    // 이미지를 디스크에서 불러와 메모리에 적재 (작업 자체가 무겁고 많은 자원을 필요로함)
    try {
      Thread.sleep(1000);
      img = path;
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.printf("%s에 있는 이미지 로딩 완료\n", path);
  }

  public void showImage() {
    // 이미지를 화면에 렌더링
    System.out.printf("%s 이미지 출력\n", img);
  }
}

class ImageViewer {
  public static void main(String[] args) {
    HighResolutionImage highResolutionImage1 = new HighResolutionImage("./img/고해상도이미지_1");
    HighResolutionImage highResolutionImage2 = new HighResolutionImage("./img/고해상도이미지_2");
    HighResolutionImage highResolutionImage3 = new HighResolutionImage("./img/고해상도이미지_3");

    highResolutionImage2.showImage();
  }
}

/// 두 번째 고해상도 이미지를 showImage 하는데 시간이 많이 느림
/// -> 이미지 준비하는 과정(loading)에서 시간을 다 뺏어 먹음
/// -> 사용자가 목록에서 이미지를 선택하기 전까지 굳이 이미지를 메모리에 준비시킬 필요가 없음
/// -> 사용자가 목록에서 선택한 이미지만 로딩시키면 되지 않을까?