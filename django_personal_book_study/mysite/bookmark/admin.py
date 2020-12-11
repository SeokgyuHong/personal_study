from django.contrib import admin
from bookmark.models import Bookmark
# Register your models here.


#models 파일의 Bookmark 테이블
#admin사이트에 어떻게 보여줄지 저장
@admin.register(Bookmark)
class BookmarkAdmin(admin.ModelAdmin):
    list_display = ('id','title','url')

