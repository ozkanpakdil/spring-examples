<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <title>Multi file upload!</title>
  </head>
  <body>

<input id="files" type="file" name="files[]"  multiple />
<button onclick="uploadFiles()">Upload</button>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<script type="text/javascript">
	function uploadFiles(){
		var form = new FormData();
		var i=0;
		$.each($("input[type='file']")[0].files, function(i, file) {
		    form.append('file', file);
		    form.append('extradata','ola'+i);
		    i++;
		});
		
		// send form data with ajax
		$.ajax({
		    type: 'POST',
		    url: '/uploadFiles',
		    cache: false,
		    contentType: false,
		    processData: false,
		    data : form,
		    success: function(result){
		        console.log(result);
		        alert('upload success');
		    },
		    error: function(err){
		        console.log(err);
		    }
		})
	}
	</script>
</body>
</html>