import React from 'react'
import Sponge from 'common/sponge'
import 'styles/common/tab/index.less'

export default class Tab extends React.Component{
	handlerActive(){
		let {
			handlerActive
		} = this.props;
		typeof handlerActive === 'function' && handlerActive();
	}
	render(){
		let {
			label,
			className,
			active,
			width,
		} = this.props,
		style = {},
		classNames = ['__tab'];

		!!className && classNames.push( className )
		!!active && classNames.push( "__tab_active" )
		!!width ? ( style.width = `${ width }px` ) : ( style.flex = 1 )

		return (

			<Sponge onClick = { () => this.handlerActive() } className = { classNames.join(' ') }> 
				{ label }
			</Sponge>
		)
	}
}