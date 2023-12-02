import styled from 'styled-components/native';


export const Container = styled.TouchableOpacity`
  width: 100%;
  height: auto;
  border-radius: 80px;
  align-items: center;
  justify-content: center;
`;

export const Image = styled.Image<{ size: "MD" | "XLL" }>`
  background-color: ${({theme}) => theme.COLORS.GRAY_400};

  width: ${props => props.size === "MD" ? "100px" : "200px"};
  height: ${props => props.size === "MD" ? "100px" : "200px"};

  border-radius: 125px;
  border-width: 3px;
  border-color: ${({theme}) => theme.COLORS.GRAY_300};
`;

export const IconPic = styled.View<{ size: "MD" | "XLL" }>`
  position: absolute;
  bottom: 10px;
  right: ${props => props.size === "MD" ? "105px" : "60px"};

  align-items: center;
  justify-content: center;

  background-color: ${({ theme }) => theme.COLORS.PRIMARY};

  border-radius: 35px;

  padding: 10px;

  z-index: 10;
`;
