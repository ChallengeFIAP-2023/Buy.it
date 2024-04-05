import { Fragment } from 'react';
import type { DropdownProps } from 'react-native-input-select/lib/typescript/types/index.types';
import Dropdown from 'react-native-input-select';
import { CaretDown } from 'phosphor-react-native';

// Theme import
import theme from '@theme/index';

// Style import
import {
  Label,
  dropdownStyle,
  textStyle,
  checkboxStyle,
  searchInputStyle,
  errorStyle,
  dropdownErrorStyle,
  modalOptions,
} from './styles';

export function CustomDropdown({ label, error, ...rest }: DropdownProps) {
  return (
    <Fragment>
      {Boolean(label) && <Label>{label}</Label>}
      <Dropdown
        dropdownStyle={dropdownStyle}
        dropdownErrorStyle={dropdownErrorStyle}
        placeholderStyle={textStyle}
        selectedItemStyle={textStyle}
        dropdownErrorTextStyle={errorStyle}
        primaryColor={theme.COLORS.PRIMARY}
        error={error}
        dropdownIcon={
          <CaretDown
            color={theme.COLORS.GRAY_200}
            weight="bold"
            size={theme.FONT_SIZE.MD}
          />
        }
        modalOptionsContainerStyle={modalOptions}
        checkboxLabelStyle={textStyle}
        searchControls={{
          textInputStyle: searchInputStyle,
          textInputContainerStyle: textStyle,
          textInputProps: {
            placeholder: 'Pesquise uma opção',
            ...rest.searchControls?.textInputProps,
            placeholderTextColor: theme.COLORS.GRAY_200,
          },
        }}
        searchInputStyle={searchInputStyle}
        checkboxStyle={checkboxStyle}
        listComponentStyles={{ listEmptyComponentStyle: textStyle }}
        listControls={{
          selectAllText: 'Selecionar todos',
          unselectAllText: 'Remover todos',
          emptyListMessage: 'Nenhuma opção encontrada',
        }}
        {...rest}
      />
    </Fragment>
  );
}
