import datetime
from django.db import models
from django.utils import timezone
# Create your models here.
#데이터베이스 모델 생성 

class Question(models.Model):
    #문자 필드 표현식
    objects = models.Manager()
    question_text = models.CharField(max_length=200)
    #데이터 필드 표현
    pub_date = models.DateTimeField('date published')
    def was_published_recently(self):
        return self.pub_date >=timezone.now()
    
    def __str__(self):
        return self.question_text
datetime.timedelta(days=1)
class Choice(models.Model):
    question = models.ForeignKey(Question,on_delete=models.CASCADE)
    chocie_text = models.CharField(max_length=200)

    #정수형 필드 기본값 0으로 설정 
    votes = models.IntegerField(default=0)
    def __str__(self):
        return self.chocie_text