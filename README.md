# Walnut_FCM_PushServer

푸시서버

1. build.gradle에 firebase-admin 의존성 추가. (implementation 'com.google.firebase:firebase-admin:9.1.0')
2. 파이어베이스 콘솔에 접속 후 프로젝트 설정
3. 서비스 계정 항목에서 새 비공개 키 생성으로 비공개 키를 생성
4. resources에 비공개 키 첨부

* 스프링부트 application.properties에서 db 연결 정보 수정

base url
/push/v1

put
/tokens
-> json 형식의 token과 email을 Body로 보내면 스프링부트에서 지정한 db에 저장

get
/tokens
-> email을 Params로 입력하면 해당 email과 연결되어있는 token 값을 반환

post
/messages
-> json 형식의 token, title, body, opcode, tokenId, msgId를 Body로 보내면 FCM서버에 전달하여 입력한 token값을 가진 기기에 입력한 title과 body를 알림으로 보내고, 스프링부트에서 지정한 db에 입력한 값들을 저장

get
/messages
-> token을 Params로 입력하면 해당 token값에 전송한 메세지의 정보들을 모두 불러올 수 있다. 추가로 start와 end값을 Params로 입력하면 그에 해당하는 인덱스 범위의 메세지들을 불러올 수 있다. start 미입력 시 처음부터 입력한 end번째까지 불러오고, end 미입력 시 입력한 start번째부터 끝까지 불러온다. start와 end 미입력 시 처음부터 끝까지 불러온다.
ex) test라는 token 값을 지닌 기기에 9개의 메세지가 전송되었다고 가정한다면,
/push/v1/messages?token=test&start=1&end=5    => 1 ~ 5번째 인덱스의 메세지들을 불러옴.
/push/v1/messages?token=test&start=3          => 3 ~ 9번째 인덱스의 메세지들을 불러옴.
/push/v1/messages?token=test&end=7            => 1 ~ 7번째 인덱스의 메세지들을 불러옴.
/push/v1/messages?token=test                  => 1 ~ 9번째 인덱스의 메세지들을 불러옴.

get
/messages/{msgId}
-> 함께 입력한 msgId 값과 연결된 메세지 정보들을 반환
