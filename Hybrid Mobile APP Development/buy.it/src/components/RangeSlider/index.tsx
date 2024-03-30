import { Fragment, useRef } from 'react';
import Slider, { SliderProps } from '@react-native-community/slider';

// Theme import
import theme from '@theme/index';

// Style import
import { Label, Value, StyledSlider } from './styles';

interface Props extends SliderProps {
  label: string;
}

export function CustomSlider({ label, ...rest }: Props) {
  const ref = useRef<Slider>(null);

  return (
    <Fragment>
      {Boolean(label) && <Label>{label}</Label>}
      <StyledSlider
        {...rest}
        minimumTrackTintColor={theme.COLORS.GRAY_400}
        maximumTrackTintColor={theme.COLORS.GRAY_400}
        thumbTintColor={theme.COLORS.PRIMARY}
        ref={ref}
      />
    </Fragment>
  );
}
