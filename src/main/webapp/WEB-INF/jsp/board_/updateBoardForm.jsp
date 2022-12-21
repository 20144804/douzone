<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정하기</title>
<script type="text/javascript">
    
</script>
<style type="text/css">

/* 넓이 높이 조절 */
.ck.ck-editor {
   	max-width: 1000px;
}
.ck-editor__editable {
    min-height: 300px;
}

</style>
<script src="https://cdn.ckeditor.com/ckeditor5/35.3.2/classic/ckeditor.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/35.3.2/classic/translations/ko.js"></script>
</head>
<body>
<form name ="updateEdit" method="post">
<!-- <form name ="updateEdit" action = "/project/updateBoard" method="post"> -->
	<label>제목</label>
	<input type="text" name="title" id="title" value="${board.title}"><br/>
	<select name='category' id="category">
		<option >선택</option>
		<option value='잡담' ${board.category == '잡담' ? 'selected' : ''}>잡담</option>
		<option value='공지사항' ${board.category == '공지사항' ? 'selected' : ''}>공지사항</option>
		<option value='Q&A' ${board.category == 'Q&A' ? 'selected' : ''}>Q&A</option>
	</select><br/>

	<label>내용</label>
	<textarea name="content" id="content">${board.content}</textarea>
	<input type="hidden" name="num" id="num" value="${board.num}">
		
	<input type="submit" value="수정사항저장" id="insertBoard">
	<input type="submit" value="수정취소">
</form>
<script>
ClassicEditor
.create( document.querySelector( '#content' ), {language : "ko"} )
.catch( error => {
    console.error( error );
} );

let insertButton = document.querySelector("#insertBoard");
insertButton.onclick = (e) => {
	e.preventDefault();
	jsInsert();
}
 

function jsInsert () {
	
	let param = {
			"num":num.value,
			"title" : title.value,
			"category" : category.value,
			"content" : document.querySelector(".ck-content").innerHTML	
		};
	
	fetch('/project/updateBoard', {
		//option
		method : 'POST',
		headers: {
		    'Content-Type': 'application/json;charset=utf-8'
		},
		body: JSON.stringify(param)//{"uid":"user10","pwd":"123", "name":"홍길동"}			
	})
	.then(response => response.json())
	.then(jsonResult => {
	
		//처리후 메시지 출력
		alert(jsonResult.message);
		location.href =  "/project/boardShow";
		
	});
}


</script> 
</body>
</html>