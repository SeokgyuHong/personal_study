from rest_framework import serializers
from snippets.models import Snippet, LANGUAGE_CHOICES, STYLE_CHOICES
from django.contrib.auth.models import User


#S각 모델들에 대해 modelserializer대신 hyperlinkedmodelserializer를 사용한다.
class SnippetSerializer(serializers.HyperlinkedModelSerializer):  # HyperlinkedModelSerializer
    owner = serializers.ReadOnlyField(source='owner.username')  # owner field 추가
    highlight = serializers.HyperlinkedIdentityField(view_name='snippet-highlight', format='html')  # hyperlinked

    class Meta:
        model = Snippet
        fields = ['url', 'id', 'highlight', 'owner', 'title', 'code', 'linenos', 'language',
                  'style']  # url, highlight 추가


class UserSerializer(serializers.HyperlinkedModelSerializer):  # HyperlinkedModelSerializer
    snippets = serializers.HyperlinkedRelatedField(many=True, view_name='snippet-detail', read_only=True)  # hyperlinked

    class Meta:
        model = User
        fields = ['url', 'id', 'username', 'snippets']  # url 추가

# class SnippetSerializer(serializers.ModelSerializer):
#     owner = serializers.ReadOnlyField(source='owner.username')
#     class Meta:
#         model = Snippet
#
#         fields = ['id', 'owner','title', 'code', 'linenos', 'language', 'style']
#
#
# class UserSerializer(serializers.ModelSerializer):
#     snippets = serializers.PrimaryKeyRelatedField(many=True, queryset=Snippet.objects.all())
# #ModelSerializer를 상속받아도 snippets를 상속받은게 아니기때문에 명시적으로 필드 지정
#     class Meta:
#         model = User
#         fields = ['id', 'username', 'snippets']
    # id = serializers.IntegerField(read_only=True)
    # title = serializers.CharField(required=False,allow_blank=True, max_length=100)
    # code = serializers.CharField(style={'base_template':'textarea.html'})
    # linenos = serializers.BooleanField(required=False)
    # language = serializers.ChoiceField(choices=LANGUAGE_CHOICES,default='python')
    # style = serializers.ChoiceField(choices=STYLE_CHOICES,default='friendly')
    #
    # def create(self, validated_data):
    #     """
    #     Create and return a new `Snippet` instance, given the validated data.
    #     """
    #     return Snippet.objects.create(**validated_data) #keyword arguments를 받아서 인스턴스화
    #
    # def update(self, instance, validated_data):
    #     """
    #     Update and return an existing `Snippet` instance, given the validated data.
    #     """
    #     instance.title = validated_data.get('title', instance.title)
    #     instance.code = validated_data.get('code', instance.code)
    #     instance.linenos = validated_data.get('linenos', instance.linenos)
    #     instance.language = validated_data.get('language', instance.language)
    #     instance.style = validated_data.get('style', instance.style)
    #     instance.save()
    #     return instance
