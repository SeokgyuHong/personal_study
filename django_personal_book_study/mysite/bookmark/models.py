from django.db import models


# Create your models here.

# models.Model 상속

class Bookmark(models.Model):
    title = models.CharField('TITLE', max_length=100, blank=True)
    url = models.URLField('URL', unique=True)

    # 객체를 문자열로 표현
    def __str__(self):
        return self.title

class Bookmark_Test(models.Model):
    title = models.CharField('TITLE', max_length=100, blank=True)
    url = models.URLField('URL', unique=True)

    # 객체를 문자열로 표현
    def __str__(self):
        return self.title
