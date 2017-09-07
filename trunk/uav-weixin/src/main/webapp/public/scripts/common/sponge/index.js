import React from 'react'
import 'styles/common/sponge/index.less'

export default class Sponge extends React.Component{
	onTouchStart( e ){
		e.currentTarget.style.background = "rgba(0, 0, 0, 0.0980392)";
	}
	onTouchEnd( e ){
		e.currentTarget.style.background = "none";
	}
	render(){
		// className
		let { 
			className, 
		} = this.props,
			classNames = ["__list"];

		!!className && classNames.push( className );

		return (
			<div className = { classNames.join(" ") } { ...this.props } onTouchStart = { e => this.onTouchStart( e ) } onTouchEnd  = { e => this.onTouchEnd( e ) }>
				{
					this.props.children
				}
			</div>
		)
	}
}