import React, {Component} from 'react';
import {connect} from 'react-redux';
import ListaReproduccion from './ListaReproduccion';
import {bindActionCreators} from 'redux';

import {getListas} from '../actions/listasReproduccionActions';

class ListasReproduccion extends Component {

    componentWillMount() {
        this.props.getListas();
    }

    render() {
        let {listas} = this.props;

        return (
            <div>
                {
                    !!listas &&
                    listas.map((lista, index) => {
                        return <ListaReproduccion lista={lista} key={index + lista.id}/>
                    })
                }
            </div>
        );
    }
}

const mapStateToProps = (state) => ({
    listas: state.perfilReducer.listasReproduccion.listas
});

const mapDispatchToProps = (dispatch) => ({
    getListas: bindActionCreators(getListas, dispatch)
});

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(ListasReproduccion);