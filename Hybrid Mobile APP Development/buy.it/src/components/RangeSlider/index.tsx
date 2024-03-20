import { Fragment } from 'react';
import { SliderProps } from '@react-native-community/slider';

// Theme import
import theme from '@theme/index';

// Style import
import {
  Label,
  Value,
  StyledSlider
} from './styles';

interface Props extends SliderProps{
  label: string;
  valueString: string | undefined;
  minimumValue: number,
  maximumValue: number,
  onValueChange: (value: number) => void;
}

export function CustomSlider({ 
  label, 
  valueString,
  minimumValue,
  maximumValue,
  onValueChange
}: Props) {
  return (
    <Fragment>
      {Boolean(label) && <Label>{label}</Label>}
      {/* TODO: verificar margem extra nos sliders */}
      <StyledSlider
        minimumTrackTintColor={theme.COLORS.GRAY_400}
        maximumTrackTintColor={theme.COLORS.GRAY_400}
        tapToSeek
        thumbTintColor={theme.COLORS.PRIMARY}
        minimumValue={minimumValue}
        maximumValue={maximumValue}
        onValueChange={onValueChange}
      />
      {Boolean(valueString) && <Value>{valueString}</Value>}
    </Fragment>
  );
}
