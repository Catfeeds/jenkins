import React from 'react'
import { LoadMore } from 'react-weui'

let container,
	currentPage = 1,
	totalPages = 1,
	_callback = function(){},
	loading = false;

function scrollHandler( e ){
	let target = e.srcElement || e.target,
		offset = 0,
		scrollTop;
	if( target === document ){
		offset = parseInt( document.body.scrollHeight ) - parseInt( window.innerHeight );
		scrollTop = parseInt( document.body.scrollTop );
	}else{
		offset = parseInt( target.scrollHeight ) - parseInt( target.clientHeight );
		scrollTop = parseInt( target.scrollTop );
	}
	if( !loading && scrollTop >= offset ){
		loading = true;
		_callback( currentPage + 1 );
	}
}

export default class MoreBar extends React.Component{
	constructor(props) {
		super(props);
		this.state = {
			stop : false,
		};
	}
	componentDidUpdate(){
		let container = this.state.container;
		if( currentPage >= totalPages && container ){
			container.removeEventListener( "scroll", scrollHandler, false );
			stop = true;
		}
		else if( stop && currentPage < totalPages && container ){
			container.removeEventListener( "scroll", scrollHandler, false );
			container.addEventListener( "scroll", scrollHandler, false );
			stop = false;
		}
	}
	componentDidMount(){
		let {
				paging
			} = this.props,
			container = findContainer( this.refs.moreBar );
		this.setState( { container } );
		if( paging && paging.currentPage < paging.totalPages ){
			container.addEventListener( "scroll", scrollHandler, false );
			stop = false;
		}
		else
			stop = true;
		function findContainer( node ){
			if( node === window || node === document || node === document.body )
				return window
			else if( window.getComputedStyle( node ).overflow.toLowerCase() === 'auto' )
				return node;
			else
				return findContainer( node.parentNode );
		}
	}
	componentWillUnmount(){
		let { container } = this.state;
		if( container ){
			container.removeEventListener( "scroll", scrollHandler, false );
			stop = true;
		}
	}
	render(){
		let {
			loadMore,
			paging,
			text,
		} = this.props;
		loadMore !== _callback && ( _callback = loadMore )
		if( paging && paging.currentPage !== currentPage ){
			currentPage = paging.currentPage;
			loading = false;
		}
		if( paging && paging.totalPages !== totalPages)
			totalPages = paging.totalPages;
		// debugger;
		return (
			<div ref = { 'moreBar' }>
				{
					currentPage >= totalPages ?
						<LoadMore showLine>没有数据了</LoadMore>
							:
						<LoadMore loading>{ text || 'Loading'}</LoadMore>
				}
			</div>
				// <div ref = { 'moreBar' } onClick = { () => this.props.loadMore( currentPage + 1 ) || function(){} } className = "more-bar">{ text || '加载更多数据'}</div>
		)
	}
}
