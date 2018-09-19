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
	
			.style1 {
				color: #000000;
				font-size: 18px;
				font-weight: bold;
			}
			.style2 {font-size: 12px}
			a {
				font-family: Geneva, Arial, Helvetica, sans-serif;
				color: #000000;
			}
			a:link {
				text-decoration: none;
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

			</style>
			</head>			
			<body>
				<table width = "100%" border = "1" bordercolor="#cccccc">			
					<!-- item 템플레이트 룰 적용 -->
					<xsl:apply-templates select="/rss/channel/item"/>
				</table>
				<!-- name 속성을 갖는 템플레이트 룰 적용 -->
				<xsl:call-template name="teamName"/> 
			</body>
			
			</xsl:when>
			<xsl:otherwise>							
				<xsl:call-template name = "notFind"/>				
			</xsl:otherwise>
		</xsl:choose>
			
		</html>
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

	<!-- item 템플레이트 룰 -->
	<xsl:template match = "item">	
		<tr>
    		<td height="35" colspan="2" bordercolor="#FFFFFF" bgcolor="#CCCCCC">
    			<span class="style1">
    				<a href="{link}" target="_blank" class="style1">
    					<xsl:value-of select = "title" disable-output-escaping='yes'/>
    				</a>
    			</span>
    		</td>
  		</tr>
  		<tr>
    		<td align = "center" valign = "middle" width="90" border = "3" bordercolor="##000000" bgcolor="#FFFFFF" >
    			<xsl:variable name = 'thumbnail_string'>
    				<xsl:value-of select = "thumbnail"/>
    			</xsl:variable>
    			<xsl:choose>
    				<xsl:when test = "string-length($thumbnail_string) &lt;= 1">
    					<strong>No<br></br>Image</strong>
    				</xsl:when>
    				<xsl:otherwise>
    					<img src = "{thumbnail}" width = "90"/>
    				</xsl:otherwise>
    			</xsl:choose>
    		</td>
    		<td height="50" bordercolor="#FFFFFF" bgcolor="#FFFFFF" class="style2">
    			<xsl:value-of select = "description" disable-output-escaping='yes'/>
    		</td>
  		</tr>		
	</xsl:template>

	<!-- name 속성을 갖는 템플레이트 룰 -->
	<xsl:template name="teamName">
		<div align="center">Copyright ⓒ 2008 Void Walkers. All rights reserved.</div>
	</xsl:template>	
</xsl:stylesheet>



