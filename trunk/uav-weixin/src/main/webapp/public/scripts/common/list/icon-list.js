import React from 'react'
import List from 'common/list'
import 'styles/common/icon-list.less'

export default class IconList extends React.Component{
	render(){
		// className
		// click
		// label
		// text
		// rightIcon
		// leftIcon
		let { 
			className, 
			click, 
			label,
			text,
			rightIcon, 
			leftIcon,
		} = this.props,
			classNames = ["__icon_list"],
			_label = (<span key = "__label" className = "__label">{label}</span>),
			_text = (<span key = "__text" className = "__text">{text}</span>);

		!!className && classNames.push( className );
		return (
			<List click = { click } className = { classNames.join(' ') }>
				{ !!leftIcon && <span className = { `__left_icon ${ leftIcon }` }></span> }
				<div className = '__list_conten'>
					{
						[ _label, _text ]
					}
				</div>
				{ !!rightIcon && <span className = { `__right_icon ${ rightIcon }` }></span> }	
			</List>
		)
	}
}