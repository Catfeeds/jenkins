import React from 'react'

export default class Breadcrumbs extends React.Component{
	render(){
		let {
				steps,
				step,
			} = this.props,
			liArray = [],
			maxIndex = 0;
			if( Array.isArray( steps ) ){
				maxIndex = steps.length - 1;
				 steps.map( ( v, k ) => {
				 	liArray.push(<li className = { ( step - 1 ) === k ? "selected" : "" } key = { `li_${k}` }>{ v }</li>);
				 	if( maxIndex !== k )
				 		liArray.push(<li key = { `li__${k}` }>&gt;</li>);
				 })
			}
		return (
			<div className="breadcrumbs">
				<ul className = "clear">{liArray}</ul>
			</div>
		)
	}
}