# -*- coding: utf-8 -*-
import cv2
def madeVidocap(a):
    vidcap = cv2.VideoCapture(a)
    length = int(vidcap.get(cv2.CAP_PROP_FRAME_COUNT))
    width = int(vidcap.get(cv2.CAP_PROP_FRAME_WIDTH))
    height = int(vidcap.get(cv2.CAP_PROP_FRAME_HEIGHT))
    fps = vidcap.get(cv2.CAP_PROP_FPS)
    FPS= int("%d"%fps)
    count=0

    while(vidcap.isOpened()):
        ret, image = vidcap.read()

        if(int(vidcap.get(1)) % int(FPS) == 0):
            str1 = str(int(vidcap.get(1)))
            cv2.imwrite("C:\\Users\\PC\\Desktop\\foif\\src\\main\\resources\\static\\video\\originaltest (%d).jpg" % count, image)
            count += 1

    vidcap.release()

    return "length : " + length + "width : " + width + "height : " + height + "fps : " +fps + "Saved frame number : " + str1 + "Saved frame%d.jpg" % count
#
# import cv2
# import os
#
# print(cv2.__version__)
# filepath = 'video\\abc.mp4'
# video = cv2.VideoCapture(filepath)
#
# if not video.isOpened():
#     print("Could not Open :", filepath)
#     exit(0)
#
# length = int(video.get(cv2.CAP_PROP_FRAME_COUNT))
# width = int(video.get(cv2.CAP_PROP_FRAME_WIDTH))
# height = int(video.get(cv2.CAP_PROP_FRAME_HEIGHT))
# fps = video.get(cv2.CAP_PROP_FPS)
#
# print("length :", length)
# print("width :", width)
# print("height :", height)
# print("fps :", fps)
#
# try:
#     if not os.path.exists(filepath[:-4]):
#         os.makedirs(filepath[:-4])
#         count = 0
#
#         while (video.isOpened()):
#             ret, image = video.read()
#             if (int(video.get(1)) % fps == 0):  # 앞서 불러온 fps 값을 사용하여 1초마다 추출
#                 cv2.imwrite(filepath[:-4] + "video\\abc (%d).jpg" % count, image)
#                 print('Saved frame number :', str(int(video.get(1))))
#                 count += 1
#
#         video.release()
# except OSError:
#     print ('Error: Creating directory. ' +  filepath[:-4])

