import cv2

# 영상의 의미지를 연속적으로 캡쳐할 수 있게 하는 class
vidcap = cv2.VideoCapture('video\\abc.mp4')

length = int(vidcap.get(cv2.CAP_PROP_FRAME_COUNT))
width = int(vidcap.get(cv2.CAP_PROP_FRAME_WIDTH))
height = int(vidcap.get(cv2.CAP_PROP_FRAME_HEIGHT))
fps = vidcap.get(cv2.CAP_PROP_FPS)
FPS= int("%d"%fps)
print("length :", length)
print("width :", width)
print("height :", height)
print("fps :", fps)

count=0

while (vidcap.isOpened()):
    # read()는 grab()와 retrieve() 두 함수를 한 함수로 불러옴
    # 두 함수를 동시에 불러오는 이유는 프레임이 존재하지 않을 때
    # grab() 함수를 이용하여 return false 혹은 NULL 값을 넘겨 주기 때문
    ret, image = vidcap.read()

    if (int(vidcap.get(1)) % int(FPS)== 0):
        print('Saved frame number : ' + str(int(vidcap.get(1))))
        cv2.imwrite("video\\abc (%d).jpg" % count, image)
        print('Saved frame%d.jpg' % count)
        count += 1


vidcap.release()
#
# import cv2
# import os
#
# print(cv2.__version__)
# filepath = 'video\\abc.mp4'
# video = cv2.VideoCapture(filepath) #'' 사이에 사용할 비디오 파일의 경로 및 이름을 넣어주도록 함
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

