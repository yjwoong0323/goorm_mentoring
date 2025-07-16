package me.james;

// 대상 객체와 프록시 객체를 묶는 인터페이스 (다형성)
interface IImage {
  void showImage(); // 이미지를 렌더링하기 위해 구현체가 구현해야 하는 추상메소드
}

// 대상 객체 (RealSubject)
class HighResolutionImage1 implements IImage {
  String img;

  HighResolutionImage1(String path) {
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

// 프록시 객체 (Proxy)
class ImageProxy implements IImage {
  private IImage proxyImage;
  private String path;

  ImageProxy(String path) {
    this.path = path;
  }

  @Override
  public void showImage() {
    // 고해상도 이미지 로딩하기
    proxyImage = new HighResolutionImage1(path);
    proxyImage.showImage();
  }
}

class ImageViewer1 {
  public static void main(String[] args) {
    IImage highResolutionImage1 = new ImageProxy("./img/고해상도이미지_1");
    IImage highResolutionImage2 = new ImageProxy("./img/고해상도이미지_2");
    IImage highResolutionImage3 = new ImageProxy("./img/고해상도이미지_3");

    highResolutionImage2.showImage();
  }
}