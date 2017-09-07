import React from 'react'
import ReactCSSTransitionGroup  from 'react-addons-css-transition-group'
import 'styles/common/tab/switch-view.less'

export default class SwitchView extends React.Component{
	render(){
		let content,
			{
				position,
			} = this.props,
			tabs = [];

		React.Children.map( this.props.children, ( child, i ) => {
			if( !child )
				return;
			tabs.push( React.cloneElement( child, { 
					key : `__tab_${ i }`,
				})
			);

			if( !!child.props.active )
				content = <div key = { i } className = '__view_content'>{ child.props.children }</div>
		});
		return (
			<div className = '__switch_view'>
				<div className={ `__view_tab ${ position }` }>
					{ tabs }
				</div>
				<ReactCSSTransitionGroup
		          transitionName="switch-view"
		          transitionEnterTimeout={300}
		          // transitionLeaveTimeout={300}
		          transitionLeave = { false }
		          >
					{ content || <div className = '__view_content'></div> }
		        </ReactCSSTransitionGroup>
			</div>
		)
	}
}