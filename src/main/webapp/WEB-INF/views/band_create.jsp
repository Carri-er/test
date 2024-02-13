<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>캐리어</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link rel="stylesheet" href="../css/common.css">
    <link rel="stylesheet" href="../css/band_create.css">
</head>
<body>
    <div class="container-fluid band_create_wrapper">
        <div class="container py-5 band_create_Box">
            <form action="band_create" method="post" enctype="multipart/form-data">
                <div class="row">
                    <div class="col">
                    	<input name="band_admin" type="hidden" value="jjangchang" />
                        <p class="h4 text-center">밴드 대표사진을 설정해주세요.</p>
                        <input name="band_thumnail" class="form-control" type="file" id="formFileMultiple"  onchange="readURL(this);" multiple >
						<img id="blah" src="http://placehold.it/180" alt="#" class="rounded-circle border" width="200" height="200"/>
                        <p class="h4 text-center">밴드 이름을 입력해주세요.</p>
                        <input name="band_name" type="text" class="form-control" placeholder="밴드 이름 입력">
                        <p class="h4 text-center">밴드 소개글을 입력해주세요.</p>
                        <textarea name="band_content" class="form-control" placeholder="밴드 소개말과 대표사이트 등을 입력하세요." id="floatingTextarea"></textarea>
                        <input type="submit" value="밴드 생성"/>
                    </div>
                </div>
            </form>
        </div> <!-- band_create_Box -->
    </div> <!-- band_create_wrapper -->
    
    
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
    <script>
    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#blah')
                    .attr('src', e.target.result);
            };

            reader.readAsDataURL(input.files[0]);
        }
    }
    </script>
</body>
</html>