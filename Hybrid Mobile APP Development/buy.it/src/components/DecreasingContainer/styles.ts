import styled from 'styled-components/native';

// Asset import
// import DecreasingImage from "@assets/decreasing02.png";

// export const Container = styled.ImageBackground.attrs({
//   source: DecreasingImage
// })`
//   height: 100%;
// `;

export const Header = styled.View`
  height: 60px;
  transform: skew(0deg, 7deg);
  background-color: ${({ theme }) => theme.COLORS.GRAY_600};
  border-top-right-radius: 10px;
  border-top-left-radius: 10px;
  margin-bottom: -25px;
  margin-top: 25px;
`;

export const Container = styled.ScrollView`

  flex: 1;

  background-color: ${({ theme }) => theme.COLORS.GRAY_600};

  padding: 0 30px 0;
`;
