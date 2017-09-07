import React from 'react'
import noDataBS64 from 'images/nodata'

export default class NoData extends React.Component{
	render(){
		return (
			<div style = {{ height : "100%", background : "#fff", textAlign : "center" }}>
				<img style = {{ marginTop : "20px", maxWidth : "100%" }} src={ noDataBS64 }/>
				<p style = {{ color : "#999", marginTop : "12px" }}>对不起！页面暂无数据</p>
			</div>
		)
	}
}