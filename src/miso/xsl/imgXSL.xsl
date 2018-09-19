<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	              xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:param name = "imgUrl"/>
	<xsl:param name = "searchWord"/>

  	<!-- 시작 템플레이트 룰 -->
	<xsl:template match="/">
		<html>
		
		<xsl:choose>
					<xsl:when test = "count(/rss/channel/item) &gt; 0">
		
			<head>
				<style type="text/css">
					a:link {
						text-decoration: none;
						color: #333333;
					}
					a:visited {
						text-decoration: none;
					}
					a:hover {
						text-decoration: none;
					}
					a:active {
						text-decoration: none;
					}
					a img {
						border : 1px solid #ffffff;
					}
				</style>
			</head>					
			<body>
				<!-- photo 템플레이트 룰 적용 -->
				<xsl:apply-templates select="/rss/channel/item"/>
				<!-- name 속성을 갖는 템플레이트 룰 적용 -->
				<br/>
				<xsl:call-template name="teamName"/> 
			</body>
			
			</xsl:when>
			<xsl:otherwise>							
				<xsl:call-template name = "notFind"/>				
			</xsl:otherwise>
		</xsl:choose>
			
		</html>
	</xsl:template>

	<!-- item 템플레이트 룰 -->
	<xsl:template match = "item">
		<xsl:variable name = "thumbnail_image">
			<xsl:value-of select = "thumbnail" disable-output-escaping = 'yes'/>
		</xsl:variable>
		<xsl:variable name = "original_image">
			<xsl:value-of select = "link" disable-output-escaping = 'yes'/>
		</xsl:variable>
		<a href = "{$original_image}" target = "_blank">
			<img src = "{$thumbnail_image}" alt = "클릭하면 원본 사이즈의  이미지가 출력됩니다."
			width = "85" height = "85"/>
		</a>			
	</xsl:template>

	<!--  notFind -->
	<xsl:template name="notFind">	
		
		<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<style type="text/css">

		.style1 {font-size: 14pt}
		.style3 {font-size: 16px}

		</style>
		</head>
		
		<body>
		<table width="362" height="441" border="2" align="center" bordercolor="#AADFAA" style="table-layout:fixed;">
		<td width="350" height="433" valign="top" style="word-break;break-all;">
		<p align="center">
		<br />
		</p>
		<p align="center"><br />
		    <img src="{$imgUrl}" width="339" height="161" /><br />
		    <b><font color="teal"><span style="font-size:12pt;"> </span></font></b></p>
		<p align="center"><b><font color="teal"><span style="font-size:12pt;"><span class="style1">
		<xsl:value-of select = "$searchWord"/></span></span></font></b></p>
		<p align="center"><b><font color="teal"><span style="font-size:12pt;"><span class="style1">단어에 대한 검색결과가 없습니다.</span></span></font></b></p>
		<table width="314" border="0" align="center">
		  <tr>
		    <td width="304"><div align="left">
		      <p class="style3">1. 단어의 철자가 정확한지 확인해 주세요.<br />
		      2. 다시 한번 검색을 시도해 주세요.</p>
		    </div></td>
		  </tr>
		</table>
		</td>
		</table>
		<div align="center">Copyright ⓒ 2008 Void Walkers. All rights reserved.
		</div>
		</body>
		
	</xsl:template>

	<!-- teamName 속성을 갖는 템플레이트 룰 -->
	<xsl:template name="teamName">
		<div align="center">Copyright ⓒ 2008 Void Walkers. All rights reserved.</div>
	</xsl:template>		
</xsl:stylesheet>