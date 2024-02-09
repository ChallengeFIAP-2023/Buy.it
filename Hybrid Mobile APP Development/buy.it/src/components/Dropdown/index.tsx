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
} from './styles';

export function CustomDropdown({ label, ...rest }: DropdownProps) {
  return (
    <Fragment>
      {Boolean(label) && <Label>{label}</Label>}
      <Dropdown
        dropdownStyle={dropdownStyle}
        placeholderStyle={textStyle}
        selectedItemStyle={textStyle}
        primaryColor={theme.COLORS.PRIMARY}
        dropdownIcon={
          <CaretDown
            color={theme.COLORS.GRAY_200}
            weight="bold"
            size={theme.FONT_SIZE.MD}
          />
        }
        modalOptionsContainerStyle={dropdownStyle}
        checkboxLabelStyle={textStyle}
        searchControls={{
          textInputStyle: searchInputStyle,
          textInputContainerStyle: textStyle,
          textInputProps: {
            placeholder: 'Pesquise uma opção',
            placeholderTextColor: theme.COLORS.GRAY_200,
          },
        }}
        searchInputStyle={searchInputStyle}
        checkboxStyle={checkboxStyle}
        listControls={{
          selectAllText: 'Selecionar todos',
          unselectAllText: 'Remover todos',
        }}
        {...rest}
      />
    </Fragment>
  );
}
