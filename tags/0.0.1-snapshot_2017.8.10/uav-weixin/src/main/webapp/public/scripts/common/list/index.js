import React from 'react'
import 'styles/common/list/index.less'

export default class List extends React.Component{
	onTouchStart( e ){
		e.currentTarget.style.background = "rgba(0, 0, 0, 0.0980392)";
	}
	onTouchEnd( e ){
		e.currentTarget.style.background = "none";
	}
	onClick( e ){
		let { click } = this.props;
		typeof click === "function" && click( e );
	}
	render(){
		// click
		// className
		let { 
			className, 
		} = this.props,
			classNames = ["__list"];

		!!className && classNames.push( className );

		return (
			<div className = { classNames.join(" ") } onClick = { e => this.onClick( e ) } onTouchStart = { e => this.onTouchStart( e ) } onTouchEnd  = { e => this.onTouchEnd( e ) }>
				{
					this.props.children
				}
			</div>
		)
	}
}