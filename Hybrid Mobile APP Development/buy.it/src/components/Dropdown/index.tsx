import { Fragment } from "react";
import type { DropdownProps } from 'react-native-input-select/lib/typescript/types/index.types';
import Dropdown from 'react-native-input-select';

// Style import
import { Label, dropdownStyle, textStyle, checkboxStyle } from './styles';
import theme from "@theme/index";

import { CaretDown } from "phosphor-react-native";

export function CustomDropdown({ label, ...rest }: DropdownProps) {
  return (
    <Fragment>
      {Boolean(label) && <Label>{label}</Label>}
      <Dropdown
        {...rest}
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
        checkboxStyle={checkboxStyle}
      />
    </Fragment>
  );
}

