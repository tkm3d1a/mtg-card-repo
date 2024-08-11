import cv2
import numpy as np

sample_images = './sample_card_images/'
file_name = 'blb-374-swamp.png'

path = sample_images + file_name

image = cv2.imread(path)
window_name = file_name

print(image.shape)
gr_image = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
window_name_gray = file_name + ' GRAYSCALE'

cv2.imshow(window_name, image)
cv2.imshow(window_name_gray, gr_image)
cv2.waitKey(0)
cv2.destroyAllWindows()
