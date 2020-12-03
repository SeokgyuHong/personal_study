from django.contrib import admin

# Register your models here.
from .models import Question
from .models import Choice

class ChoiceInline(admin.TabularInline):
    model = Choice
    extra = 3 #선택사항 슬롯 갯수 설정 

class QuestionAdmin(admin.ModelAdmin):
    fieldsets = [
        ('Question_text_information',               {'fields': ['question_text']}),
        ('Date information', {'fields': ['pub_date']}),
    ]
    #question change 변경
    list_display = ('question_text', 'pub_date','was_published_recently')
    list_filter = ['pub_date']
    #서치 필드 추가 
    search_fields = ['question_text']
    inlines=[ChoiceInline]


admin.site.register(Question, QuestionAdmin)