package util;

public class MapUtil {
	public static String getMap(String add, String name) {
		String ret = "";
		ret+="<div class='size'>\n"
				+ "        		<div id='map' style='width:100%;height:500px;'></div>\n"
				+ "        		<script type='text/javascript' src='//dapi.kakao.com/v2/maps/sdk.js?appkey=2c8069444d499a50b3d6619f421fef73&libraries=services'></script>\n"
				+ "        		<script>\n"
				+ "					var container = document.getElementById('map');\n"
				+ "					var options = {\n"
				+ "						center: new kakao.maps.LatLng(37.58017097279185, 126.97796092598705),\n"
				+ "						level: 3\n"
				+ "					};\n"
				+ "			\n"
				+ "					var map = new kakao.maps.Map(container, options);\n"
				+ "					// 주소-좌표 변환 객체를 생성합니다\n"
				+ "					var geocoder = new kakao.maps.services.Geocoder();\n"
				+ "\n"
				+ "					// 주소로 좌표를 검색합니다\n"
				+ "					geocoder.addressSearch('"+add+"', function(result, status) {\n"
				+ "\n"
				+ "						// 정상적으로 검색이 완료됐으면 \n"
				+ "					     if (status === kakao.maps.services.Status.OK) {\n"
				+ "	\n"
				+ "					        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);\n"
				+ "	\n"
				+ "					        // 결과값으로 받은 위치를 마커로 표시합니다\n"
				+ "					        var marker = new kakao.maps.Marker({\n"
				+ "					            map: map,\n"
				+ "					            position: coords\n"
				+ "					        });\n"
				+ "	\n"
				+ "					        // 인포윈도우로 장소에 대한 설명을 표시합니다\n"
				+ "					        var infowindow = new kakao.maps.InfoWindow({\n"
				+ "					            content: '<div style=\"width:150px;text-align:center;padding:6px 0;\">"+name+"</div>'\n"
				+ "					        });\n"
				+ "					        infowindow.open(map, marker);\n"
				+ "	\n"
				+ "					        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다\n"
				+ "					        map.setCenter(coords);\n"
				+ "					    } \n"
				+ "					});    \n"
				+ "				</script>\n"
				+ "        	</div>";
		return ret;
	}
}
