<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src ="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
	
	<h1>기상청 예보</h1>
	
	
	<%-- 만약에 option에 value를 지정하지 않으면 태그내 정보가 value! --%>
	
	지역:
	
	<select id="stnId">
		<option value="108">전국<option/>
		<option value="109">서울, 인천, 경기도<option/>
		<option value="156">광주, 전라남도<option/>
		<option value="144">제주도<option/>
	</select>
	
	<button id="btn">해당 지역 에보 정보</button>
	
	<br><br>
	
	<table border="1">
		<thead>
			<tr>
				<th>예보 정보</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td></td>
			</tr>
		</tbody>
	</table>
	
	
	<script>
		$(function() {
			$("#btn").click(function() {
				$.ajax({
					url : "api.do",
					data : {stnId: $("#stnId").val()},
					success: function(data){
						console.log(data.response.body.items.item);
						const result = data.response.body.items.item;
						const dataList = result[0].wfSv.split("○ ");
						
						let value = "";
						
						for(let index in dataList){
							console.log(dataList[index]);
							
							if(dataList[index] !== ""){
								value += "<tr> <td>" 
									+ result[0].wfSv +
									"<td/> <tr/>"
							}
							
						}
						
						
								
						$("table tbody").html(value);
					}
				})
			})
		})
	</script>
</body>
</html>