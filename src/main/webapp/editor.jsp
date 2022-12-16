<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
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
<script src="https://code.jquery.com/jquery-2.2.4.min.js" integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44=" crossorigin="anonymous"></script>

</head>
<body>

<form name ="edit" id="edit" action = "/project/write" method="post" enctype="multipart/form-data">
	<label>제목</label>
	<input type="text" name="title" id="title" ><br/>
	<select name='category' id="category">
		<option disabled selected>선택</option>
		<option value='잡담'>잡담</option>
		<option value='공지사항'>공지사항</option>
		<option value='Q&A'>Q&A</option>
	</select><br/>
	<label>내용</label>
	<textarea name="content" id="editor"></textarea>
<!-- 	 첨부파일:<input type ="file" id ="uploadFile" name="uploadFile" > -->
 <table>
      <tbody>

      <tr>
            <th><label>첨부파일</label></th>
            <td><input type="file" name="uploadFile" id="uploadFile"/></td>
            <td><input type="button" value="추가" class="insertFile"></td>
         </tr>
      </tbody>
      <tfoot>
         <tr style="display:none">
            <th><label>첨부파일</label></th>
            <td><input type="file" name="uploadFile" id="uploadFile"/></td>
            <td><input type="button" value="추가" class="insertFile"></td>
            <td><input type="button" value="삭제" class="deleteFile"></td>
         </tr>
      </tfoot>
   </table>
   <br/>
   <input type="submit" id="write" name="write" value="전송"  > 
   <input type="button" name="cancle" value="취소" onClick="history.back()">

	<!-- <input type="submit" value="전송"> -->
</form>
<script>
let tbody = document.querySelector("tbody");
let tr = document.querySelector("tfoot tr");
let insertFile = document.querySelector(".insertFile");

function insertFileEventHandler() {
   let newTr = tr.cloneNode(true);
   tbody.append(newTr);
   newTr.style.display = "";
   
   newTr.querySelector(".insertFile").addEventListener("click", insertFileEventHandler);
   newTr.querySelector(".deleteFile").addEventListener("click", e => {
      tbody.removeChild(e.target.parentNode.parentNode)
   });
}  

insertFile.addEventListener("click", insertFileEventHandler);

        ClassicEditor
            .create( document.querySelector( '#editor' ), {language : "ko"} )
            .catch( error => {
                console.error( error );
        } );

       let edit = document.querySelector("#edit");
        edit.addEventListener("submit", (e) => {
           e.preventDefault();        

        		   fetch("/project/write", 
        		      {
        		         method: 'POST',
        		          cache: 'no-cache',
        		          body: new FormData(edit)
        		      })
        		      .then(response => response.json())
        		      .then(jsonResult => {
        		    	 console.log(jsonResult.status);
        		    	 alert(jsonResult.message);
        		         if(jsonResult.status)
        		         	location.href =  jsonResult.url;
        		         
        		      });
        		});  
        		
/*   $(function() {
	 
	 $("#write").click(function(){
		 var _data = $("form[name=edit]").serialize()
		 $.ajax({
        				type:"post",
        				dataType:"json",
        				async:false,
        				url:"http://localhost:8800/project/write",
        				data:{data:_data},
        				success:function(){
        					
        				},
        				error:function(){
        					alert("에러 발생");
        				}
        				complete:function(){
        					
        				}
        			}) 
	 })
 }) */
     
        		
        				
        		
        		
</script>
</body>
</html>