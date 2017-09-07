import React from 'react'
import 'styles/common/tab/banner.less'

export default class TabBanner extends React.Component{
	render(){
		return (
			<div className = "__scroll_view_banner">
				<ul>
					<li>&nbsp;</li>
					<li>{ this.props.label }</li>
					<li>&nbsp;</li>
				</ul>
			</div>
		)
	}
}