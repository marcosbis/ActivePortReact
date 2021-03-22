import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITemplateConfiguration, defaultValue } from 'app/shared/model/template-configuration.model';

export const ACTION_TYPES = {
  FETCH_TEMPLATECONFIGURATION_LIST: 'templateConfiguration/FETCH_TEMPLATECONFIGURATION_LIST',
  FETCH_TEMPLATECONFIGURATION: 'templateConfiguration/FETCH_TEMPLATECONFIGURATION',
  CREATE_TEMPLATECONFIGURATION: 'templateConfiguration/CREATE_TEMPLATECONFIGURATION',
  UPDATE_TEMPLATECONFIGURATION: 'templateConfiguration/UPDATE_TEMPLATECONFIGURATION',
  DELETE_TEMPLATECONFIGURATION: 'templateConfiguration/DELETE_TEMPLATECONFIGURATION',
  SET_BLOB: 'templateConfiguration/SET_BLOB',
  RESET: 'templateConfiguration/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITemplateConfiguration>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type TemplateConfigurationState = Readonly<typeof initialState>;

// Reducer

export default (state: TemplateConfigurationState = initialState, action): TemplateConfigurationState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TEMPLATECONFIGURATION_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TEMPLATECONFIGURATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_TEMPLATECONFIGURATION):
    case REQUEST(ACTION_TYPES.UPDATE_TEMPLATECONFIGURATION):
    case REQUEST(ACTION_TYPES.DELETE_TEMPLATECONFIGURATION):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_TEMPLATECONFIGURATION_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TEMPLATECONFIGURATION):
    case FAILURE(ACTION_TYPES.CREATE_TEMPLATECONFIGURATION):
    case FAILURE(ACTION_TYPES.UPDATE_TEMPLATECONFIGURATION):
    case FAILURE(ACTION_TYPES.DELETE_TEMPLATECONFIGURATION):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_TEMPLATECONFIGURATION_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_TEMPLATECONFIGURATION):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_TEMPLATECONFIGURATION):
    case SUCCESS(ACTION_TYPES.UPDATE_TEMPLATECONFIGURATION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_TEMPLATECONFIGURATION):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.SET_BLOB: {
      const { name, data, contentType } = action.payload;
      return {
        ...state,
        entity: {
          ...state.entity,
          [name]: data,
          [name + 'ContentType']: contentType,
        },
      };
    }
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/template-configurations';

// Actions

export const getEntities: ICrudGetAllAction<ITemplateConfiguration> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_TEMPLATECONFIGURATION_LIST,
    payload: axios.get<ITemplateConfiguration>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<ITemplateConfiguration> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TEMPLATECONFIGURATION,
    payload: axios.get<ITemplateConfiguration>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ITemplateConfiguration> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TEMPLATECONFIGURATION,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITemplateConfiguration> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TEMPLATECONFIGURATION,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITemplateConfiguration> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TEMPLATECONFIGURATION,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const setBlob = (name, data, contentType?) => ({
  type: ACTION_TYPES.SET_BLOB,
  payload: {
    name,
    data,
    contentType,
  },
});

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
