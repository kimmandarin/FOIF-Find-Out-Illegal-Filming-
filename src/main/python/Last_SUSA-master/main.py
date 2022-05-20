import cv2
import numpy as np
from matplotlib import pyplot as plt

# 각 환경에 따라 Threshold 결정하는 부분
corr, inter, bhatt, sim, a = 0, 0, 0, 0, 0

corr = 0.47
inter = 0.48
bhatt = 0.52

query_number = int(input("Query frame의 총 개수:"))
c_n = int(input("compare frame의 총 개수:"))

# 연속구간 구하기
if c_n >= 0 and c_n < 200:
    contin_f = 21

elif c_n >= 200 and c_n < 400:
    contin_f = 50

elif c_n >= 400 and c_n < 600:
    contin_f = 100

elif c_n >= 600 and c_n < 800:
    contin_f = 150

elif c_n >= 800:
    contin_f = 200

query_point, compare_point = 0, 0

if c_n < contin_f:
    x = 1
    # break 하고 순차적으로 진행.

else:
    x = c_n // 2
    while (query_point < query_number + 1 and compare_point < c_n + 1):

        query = cv2.imread('query\\query (%d).jpg' % (query_point + 1))
        test = cv2.imread('compare\\compare (%d).jpg' % (x + compare_point))
        # 전처리
        imgs = [query, test]
        hists = []

        for i, img in enumerate(imgs):
            hsv = cv2.cvtColor(img, cv2.COLOR_BGR2HSV)
            hist = cv2.calcHist([hsv], [0, 1], None, [180, 256], [0, 180, 0, 256])
            cv2.normalize(hist, hist, 0, 1, cv2.NORM_MINMAX)
            hists.append(hist)

        query = hists[0]
        test = hists[1]

        # 상관관계, 교차, 바타차야 비교
        ret_C = cv2.compareHist(query, hist, cv2.HISTCMP_CORREL)

        ret_I = cv2.compareHist(query, hist, cv2.HISTCMP_INTERSECT)
        ret_I = ret_I / np.sum(query)

        ret_B = cv2.compareHist(query, hist, cv2.HISTCMP_BHATTACHARYYA)
        # Threshold 범위내에 속하는지 검사.
        print("비교전: query_number=", query_number, "\nc_n=", c_n, "\ncontin_f=", contin_f, "\nquery_point=", query_point,
              "\ncompare_point= ", compare_point, "\na=", a, "\nx=", x)
        if ret_C >= corr and ret_I >= inter and ret_B <= bhatt:
            print("\n\n%d번 유사." % (sim + 1), "\nquery영상중 %d번째 프레임과" % (query_point + 1),
                  "test영상 중 %d번째 프레임이 유사합니다." % (x + compare_point))
            print("'CORREL': %11.2f " % (ret_C))
            print("'INTERSECT': %8.2f " % (ret_I))
            print("'BHATTACHARYYA': %.2f " % (ret_B))

            sim += 1
            query_point += 1
            compare_point += 1

        else:
            compare_point += 1
        hists.clear()

        if (c_n // 2) + (a + 1) >= c_n - (a * contin_f):
            if (x + compare_point) == c_n - (contin_f * (a + 1)):
                compare_point = 0
                x = 1 + (a * contin_f)
        elif x == (c_n // 2) + (a * contin_f) and compare_point == contin_f:
            compare_point = 0
            x = 1 + (a * contin_f)

        # 알고리즘 손보기 39 다음으로 안읽힘
        if (1 - (a + 1) * contin_f >= c_n // 2):
            if (x + compare_point) == c_n // 2:
                break
        elif (x == 1 + a * contin_f) and compare_point == contin_f:
            compare_point = 0
            x = c_n - ((a + 1) * contin_f)

        if (c_n - (a + 1) * contin_f <= c_n // 2 + (a + 1) * contin_f):
            if (x + compare_point) == c_n - (a - 1) * contin_f:
                compare_point = 0
                x = 1 + a * contin_f
        elif (x == c_n - (a + 1) * contin_f) and compare_point == contin_f:
            a += 1
            compare_point = 0
            x = c_n // 2 + a * contin_f

        if sim >= 10:
            print("\n\n\n*******두 영상은 동일한 영상입니다.*********")
            break

        print("\n 비교후:query_number=", query_number, "\nc_n=", c_n, "\ncontin_f=", contin_f, "\nquery_point=", query_point,
              "\ncompare_point= ", compare_point, "\na=", a, "\nx=", x)
        print("\n")

        '''
              #두 이미지 비교
              query_num,test_num=1,1
              while(query_num<6 and test_num<6): # query 이미지랑 test이미지 전체 개수.

                  query = cv2.imread('C:\\Users\\SOO\\query\\query (%d).jpg' % query_num)
                  test = cv2.imread('C:\\Users\\SOO\\compare\\compare_%d.jpg' % test_num)

                  #전처리
                  imgs = [query, test]
                  hists = []

                  for i, img in enumerate(imgs):
                      hsv = cv2.cvtColor(img, cv2.COLOR_BGR2HSV)
                      hist = cv2.calcHist([hsv], [0, 1], None, [180, 256], [0, 180, 0, 256])
                      cv2.normalize(hist, hist, 0, 1, cv2.NORM_MINMAX)
                      hists.append(hist)

                  query = hists[0]
                  test = hists[1]

                  #상관관계, 교차, 바타차야 비교
                  ret_C = cv2.compareHist(query, hist, cv2.HISTCMP_CORREL)

                  ret_I = cv2.compareHist(query, hist, cv2.HISTCMP_INTERSECT)
                  ret_I = ret_I / np.sum(query)

                  ret_B = cv2.compareHist(query, hist, cv2.HISTCMP_BHATTACHARYYA)


                   #Threshold 범위내에 속하는지 검사.
                  if ret_C>corr and ret_I>inter and ret_B<bhatt:
                      print("\n\nquery영상중 %d번째 프레임과"%query_num,"test영상 중 %d번째 프레임이 유사합니다."%test_num)
                      print("'CORREL': %11.2f "%(ret_C))
                      print("'INTERSECT': %8.2f " % (ret_I))
                      print("'BHATTACHARYYA': %.2f " % (ret_B))

                      sim+=1
                      query_num+=1
                      test_num+=1


                  else:
                      test_num+=1

                  hists.clear()
                  if sim>=10:
                      print("두 영상은 동일한 영상입니다.")

                      break

              '''

'''
methods = {'CORREL' :cv2.HISTCMP_CORREL, 'INTERSECT':cv2.HISTCMP_INTERSECT, 'BHATTACHARYYA':cv2.HISTCMP_BHATTACHARYYA}
for j, (name, flag) in enumerate(methods.items()):
    print('%-10s'%name, end='\t')
    for i, (hist, img) in enumerate(zip(hists, imgs)):
        #---④ 각 메서드에 따라 img1과 각 이미지의 히스토그램 비교
        ret = cv2.compareHist(query, hist, flag)
        if flag == cv2.HISTCMP_INTERSECT: #교차 분석인 경우
            ret = ret/np.sum(query)        #비교대상으로 나누어 1로 정규화
        print("  %7.2f"% ( ret), end='\t')
    print()
plt.show()
i= int(input("입력:"))
if i==1:
    for k in range(3,4):
        test=cv2.imread('C:\\Users\\SOO\\compare\\compare_%d.jpg'%k)
        print("%d"%k)
'''