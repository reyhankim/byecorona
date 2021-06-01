# COVID-19 Social Distancing and Face Mask Detection

This component is used to detect face mask and social distancing violations from input image, video and webcam feeds, developed as part of Bangkit 2021 Capstone Project solution from group **B21-CAP0365**. **As we do not have any active Machine Learning path members**, the machine learning part of this component is taken from [Rakesh's implementation of COVID-19 social distancing and face mask detection](https://github.com/RakeshRaj97/covid-19-face-mask-and-social-distancing-detection). **We then modified it: added API integration to POST health protocol violation to a backend, retrained face mask detection module to improve accuracy, implemented a higher-resolution output, and added health violation logging.**

## Contributors
Rakesh takes full credit for the machine learning model implementation as most of the Machine Learning source code is taken from the GitHub repository above. However, modifications in this component are done by:
* Reyhan Naufal Hakim (C0020092) - Bangkit 2021 Cloud Computing Path (B21-CAP0365) - Bandung Institute of Technology (ITB)

## Models and Techniques
Pretrained **Yolov3** is used to detect people

Pretrained **SSD** is used to detect faces

Trained **MobileNetV2** is used as face mask classifier

**Euclidean distance** is used to calculate social distancing violations

## Getting Started
### Prerequisites
* face-detection
* flask
* imutils
* keras
* matplotlib
* opencv-python
* pandas
* scikit-learn
* tensorflow
* opencv-python
* tf-nightly-build (For CUDA)

## Quickstart
Download the pretrained Yolov3 weights using this [link](https://drive.google.com/file/d/1gqdAighUzlkg-ogA8PWRuPfOH0y8OpMI/view?usp=sharing) and save it to the `yolo-coco/` directory

Create a virtual environment and install the required dependencies using the command `pip install -r requirements.txt`

### Face mask and social distancing detection on videos/webcam
Use the command `python video.py --video input/video/path --distance [default=100.0] --frames [default=20]` to test on video files

Experiment `--distance` value for different video files and `--frames` to skip frames. The result frames are stored in `result_frames/` directory

## Train Face Mask Detector
### Train
Use the command `python train_mask_detector.py --dataset input/dataset/path` to train the face mask classifier

#### References
*https://github.com/RakeshRaj97/covid-19-face-mask-and-social-distancing-detection

*https://www.pyimagesearch.com/2020/06/01/opencv-social-distancing-detector/

*https://www.pyimagesearch.com/2020/05/04/covid-19-face-mask-detector-with-opencv-keras-tensorflow-and-deep-learning/

