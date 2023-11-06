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

<h2>실시간 대기오염 정보</h2>

지역 :

<select id = "location">

<option value="서울">서울</option>

<option value="부산">부산</option>

<option value="대전">대전</option>

</select> <%-- 내용이 많아 3개만 option 지정 --%>

<button id = "btn1">해당 지역 대기오염 정보</button>

<br><br>

<table id = "result1" border = "1">

<thead>

<tr>

<th>측정소명</th>

<th>측정일시</th>

<th>통합대기환경수치</th>

<th>미세먼지농도</th>

<th>일산화탄소농도</th>

<th>이산화질소농도</th>

<th>아황산가스농도</th>

<th>오존농도</th>

</tr>

</thead>

<tbody>

</tbody>

</table>

<script>

$(function() {
	$("#btn1").click(function() {
		$.ajax({
			url : "air.do",
			data : {location: $("#location").val()},
			success: function(data){
				
				const results = data.response.body.items;
				//console.log(results);
				//const dataList = result[0].wfSv.split("○ ");
				
				let value = "";
				
				for(let index in results){
					console.log(results[index]);
					
					if(results[index] !== null){
						value += "<tr>" +
			              "<td>" + results[index].stationName + "</td>" +
			              "<td>" + results[index].dataTime + "</td>" +
			              "<td>" + results[index].khaiValue + "</td>" +
			              "<td>" + results[index].pm10Value + "</td>" +
			              "<td>" + results[index].coValue + "</td>" +
			              "<td>" + results[index].no2Value + "</td>" +
			              "<td>" + results[index].so2Value + "</td>" +
			              "<td>" + results[index].o3Value + "</td>" +
			              "</tr>";
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