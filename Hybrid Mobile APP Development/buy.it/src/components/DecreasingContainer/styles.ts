import { Dimensions } from 'react-native';
import { css } from 'styled-components';
import styled from 'styled-components/native';

const windowHeight = Dimensions.get('window').height;

export const Header = styled.View`
  height: 60px;
  transform: skew(0deg, 7deg);
  background-color: ${({ theme }) => theme.COLORS.GRAY_600};
  margin-bottom: -35px;
  margin-top: 25px;
`;

export const Container = styled.View<{ scrollable?: boolean }>`
  flex: 1;

  ${props =>
    props.scrollable &&
    css`
      min-height: 600px;
    `}

  background-color: ${({ theme }) => theme.COLORS.GRAY_600};

  padding: 0 30px;
`;
