import React from 'react'
import Slot from 'common/slot'
import Banner from './banner'
import 'styles/common/tab/scroll-view.less'

let timer,
	clientRect,
	tab,
	scrollView,
	scrollViewContent,
	startTop = 0,
	active = 0;

const page = {
	x : null,
	y : null,
};

export default class ScrollView extends React.Component{
	constructor(props) {
		super(props);
		this._touchstartHandle = null;
		this._scrollHandle = null;
		this._touchendHandle = null;
		this.state = {
			active : 0,
			position : "absolute",
		};
	}
	touchendHandle( e ){
		if( e.changedTouches[0].pageX === page.x && e.changedTouches[0].pageY === page.y )
			return;
		if( /tab_li/.test( e.target.className ) )
			return;
		clearInterval( timer )
		active = 0;
		startTop = scrollView.getBoundingClientRect().top;

		timer = setInterval( () => {
			if( startTop === scrollView.getBoundingClientRect().top ){
				clearInterval( timer )
				Array.from( scrollViewContent.children ).some( ( child, i ) => {
					if( parseInt( child.getBoundingClientRect().top ) <= 0 ){
						active = i;
					}else
						return true;
				})
				if( scrollView.getBoundingClientRect().top > 0 )
					tab.style.display = "none";

				active !== this.state.active && this.setState( { active } )
			}
			startTop = scrollView.getBoundingClientRect().top;
		}, 100)
	}
	scrollHandle( e ){
		clientRect = scrollView.getBoundingClientRect();
		if( parseInt( clientRect.top ) >= 0 ){
			if( this.state.position === "fixed" ){
				this.setState( Object.assign( {}, this.state, { position : "absolute" } ) );
			}
			return;
		}
		else if( this.state.position !== "fixed" ){
			this.setState( Object.assign( {}, this.state, { position : "fixed" } ) );
		}
	}
	// 微信滚动跟随必须
	touchstartHandle( e ){
		if( tab.style.display === "none" )
			tab.style.display = "flex";
		page.x = e.touches[0].pageX
		page.y = e.touches[0].pageY
	}
	componentWillUnmount(){
		window.removeEventListener("touchstart", this._touchstartHandle ,false);
		window.removeEventListener("touchend", this._touchendHandle ,false);
		// window.removeEventListener("scroll", this.scrollHandle ,false);
		window.removeEventListener("touchmove", this._scrollHandle ,false);
	}
	componentDidMount(){
		this._touchstartHandle = e => this.touchstartHandle( e )
		this._scrollHandle = e => this.scrollHandle( e )
		this._touchendHandle = e => this.touchendHandle( e )
		tab = this.refs.tab;
		scrollView = this.refs.scrollView;
		scrollViewContent = this.refs.scrollViewContent;
		window.addEventListener("touchstart", this._touchstartHandle ,false)
		window.addEventListener("touchend", this._touchendHandle ,false)
		// window.addEventListener('scroll', this.scrollHandle ,false);
		window.addEventListener('touchmove', this._scrollHandle ,false);

	}
	handlerActive( i , hash, handlerActiveCb ){
		clearInterval( timer )
		if( typeof handlerActiveCb === 'function' ){
			let promise = handlerActiveCb( i );
			if( Tool.isPromise( promise ) ){
				return promise.then( () => {
					this.setState({ active : i, position : 'absolute' });
					location.replace( [ location.toString().split("#")[0], hash ].join("#") );
				})
			}
		}
		this.setState({ active : i, position : 'absolute' });
		location.replace( [ location.toString().split("#")[0], hash ].join("#") );
	}
	render(){
		let {
			children
		} = this.props,
		tabs = [],
		content = [],
		__tab,
		index = 0;

		React.Children.map( children, child => {
			if( !child )
				return;
			let _index = index;
			tabs.push( __tab = React.cloneElement( child, { 
					key : `scroll_view_tab_${ _index }`, 
					active : ( this.state.active === _index ), 
					handlerActive : (() => this.handlerActive( _index, `__scroll_view_content_${ _index }`, child.props.handlerActive )) 
				})
			);
			content.push( React.cloneElement( <span>{ __tab.props.children }</span>, { key : `scroll_view_content_${ index }` }) );
			index++;
		});

		return (
			<div ref = 'scrollView' className = '__scroll_view'>
				<div style = { { zIndex: 4 }} ref = 'tab' className = { `__scroll_view_tab ${ this.state.position }` }>
				{
					tabs
				}
				</div>
				<div className = '__scroll_view_tab'>
				{
					tabs
				}
				</div>
				<div ref = 'scrollViewContent' className = '__scroll_view_content'>
					{
						content.map( ( v,i ) => {
							return (
								<div key={ `scroll_view_content_wrap_${i}`} id = { `__scroll_view_content_${ i }` }> 
									{ i !== 0 && <Banner label = { tabs[i].props.label }/> }
									{ v }
								</div>
							)
						})
					}
				</div>
			</div>
		)
	}
}